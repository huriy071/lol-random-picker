package com.example.picker.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.picker.common.data.ChampionsData;
import com.example.picker.data.champion.Champion;
import com.example.picker.data.champion.Champions;

import lombok.Getter;
import lombok.Setter;

@RestController
public class PickerClickController {
    Random rand = new java.util.Random();

    @Getter
    @Setter
    class Responce {
        Champion champ;
        String text;
    }

    @PostMapping()
    public List<Responce> post() {
        // ファイターorタンクが最低一人選択されるようにする
        Champions champs = ChampionsData.copy();
        List<Responce> picks = new ArrayList<>();
        // 4回選出後にファイターorタンクがいなければファイターorタンクから選出
        for (int i = 0; i < 4; i++) {
            Responce res = new Responce();
            res.setChamp(pick(champs));
            res.setText("あとからつくる");
            picks.add(res);
        }
        Optional<Responce> tf = picks.stream()
                .filter(t -> t.getChamp().getTags().contains("Fighter") || t.getChamp().getTags().contains("Tank"))
                .findFirst();

        if (tf.isEmpty()) {
            List<Champion> tfList = champs.getChampions().stream()
                    .filter(t -> t.getTags().contains("Fighter") || t.getTags().contains("Tank")).toList();
            Responce res = new Responce();
            res.setChamp(pick(tfList));
            res.setText("あとからつくる");
            picks.add(res);
        } else {
            Responce res = new Responce();
            res.setChamp(pick(champs));
            res.setText("あとからつくる");
            picks.add(res);
        }
        // ファイタータンク不在の時サポートに回るためシャッフルで解決させる
        List<Responce> copy = new ArrayList<Responce>(picks);
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
