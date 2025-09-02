package com.example.picker.data.champion;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Champions {
    private List<Champion> champions = new ArrayList<>();
    
    public void add(Champion champion) {
        champions.add(champion);
    }
}
