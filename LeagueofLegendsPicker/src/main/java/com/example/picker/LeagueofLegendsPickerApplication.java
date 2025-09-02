package com.example.picker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.picker.service.riotapi.LegendsJson;

@SpringBootApplication
public class LeagueofLegendsPickerApplication {
    
    public static void main(String[] args) throws Exception {
        LegendsJson championsSet = new LegendsJson();
        championsSet.init();
		SpringApplication.run(LeagueofLegendsPickerApplication.class, args);
	}

}
