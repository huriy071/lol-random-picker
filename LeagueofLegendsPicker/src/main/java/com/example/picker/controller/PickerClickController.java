package com.example.picker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.picker.data.pickclick.PickClickResponce;
import com.example.picker.service.pickclick.PickClickService;

@RestController
public class PickerClickController {
    @Autowired
    PickClickService clickService;

    @PostMapping()
    public List<PickClickResponce> post() {
        List<PickClickResponce> responce = clickService.onClick();
        return responce;
    }

    
}
