package com.example.picker.common.data;

import com.example.picker.data.champion.Champion;
import com.example.picker.data.champion.Champions;

public class ChampionsData {
    private static Champions champions;
    
    public static Champions getChampions() {
        return champions;
    }
    
    public static void setChampions(Champions c) {
        champions = c;
    }
    
    public static Champions copy() {
        Champions c = new Champions();
        for(Champion champ : champions.getChampions()) {
            c.add(champ);
        }
        return c;
    }
}
