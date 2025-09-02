package com.example.picker.service.riotapi;

import java.io.IOException;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.picker.common.constant.RiotAPIConstants;
import com.example.picker.common.data.ChampionsData;
import com.example.picker.data.champion.Champion;
import com.example.picker.data.champion.Champions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LegendsJson {
    
    public void init() throws Exception {
        // リクエストの送信
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate
                .exchange(RiotAPIConstants.CHAMPIONS, HttpMethod.GET, null, String.class);
        if (!response.hasBody()) {
            throw new Exception("champions.jsonが取得できませんでした");
        }
        ObjectMapper mapper = new ObjectMapper();
        Champions champions = new Champions();
        try {
            JsonNode datas = mapper.readTree(response.getBody()).get("data");
            // 多分こっちのほうが実行速度早いからやむなく複数行ラムダ
            datas.fieldNames().forEachRemaining(t -> {
                Champion cham = new Champion();
                cham.setName(t);
                champions.add(cham);
            });
            
            // 全チャンピオンデータ抽出
            for(Champion c : champions.getChampions()) {
                String name = c.getName();
                JsonNode info = datas.get(name);
                c.setJpName(info.get("name").asText());
                String[] tags;
                try {
                    tags = mapper.readValue(info.get("tags").traverse(), String[].class);
                    for(String s : tags) {
                        c.addTag(s);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
            
            ChampionsData.setChampions(champions);
            ChampionsData.getChampions().getChampions().forEach(System.out::println);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    
}
