package com.example.picker.service.pickclick;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.picker.common.data.ChampionsData;
import com.example.picker.data.champion.Champion;
import com.example.picker.data.champion.Champions;
import com.example.picker.data.pickclick.PickClickResponce;

@Service
public class PickClickService {
    Random rand = new java.util.Random();
    
    public List<PickClickResponce> onClick(){
     // ファイターorタンクが最低一人選択されるようにする
        Champions champs = ChampionsData.copy();
        List<PickClickResponce> picks = new ArrayList<>();
        // 4回選出後にファイターorタンクがいなければファイターorタンクから選出
        for (int i = 0; i < 4; i++) {
            PickClickResponce res = new PickClickResponce();
            res.setChamp(pick(champs));
            res.setText("");// 指示テキスト
            picks.add(res);
        }
        Optional<PickClickResponce> tf = picks.stream()
                .filter(t -> t.getChamp().getTags().contains("Fighter") || t.getChamp().getTags().contains("Tank"))
                .findFirst();

        if (tf.isEmpty()) {
            List<Champion> tfList = champs.getChampions().stream()
                    .filter(t -> t.getTags().contains("Fighter") || t.getTags().contains("Tank")).toList();
            PickClickResponce res = new PickClickResponce();
            res.setChamp(pick(tfList));
            res.setText("");// 指示テキスト
            picks.add(res);
        } else {
            PickClickResponce res = new PickClickResponce();
            res.setChamp(pick(champs));
            res.setText("");// 指示テキスト
            picks.add(res);
        }
        // ファイタータンク不在の時サポートに回るためシャッフルで解決させる
        List<PickClickResponce> copy = new ArrayList<PickClickResponce>(picks);
        Collections.shuffle(copy);
        return copy;
    }
    
    private Champion pick(Champions champs) {
        int size = champs.getChampions().size();
        int value = rand.nextInt(size);
        Champion champ = champs.getChampions().remove(value);
        return champ;
    }

    private Champion pick(List<Champion> champs) {
        int size = champs.size();
        int value = rand.nextInt(size);
        Champion champ = champs.get(value);
        return champ;
    }
}
