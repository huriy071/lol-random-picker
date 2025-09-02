package com.example.picker.data.champion;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Champion {
    private String name;
    private String jpName;
    private List<String> tags = new ArrayList<>();
    
    public void addTag(String tag) {
        tags.add(tag);
    }
}
