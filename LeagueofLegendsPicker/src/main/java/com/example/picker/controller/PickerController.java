package com.example.picker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.picker.common.constant.RiotAPIConstants;

@Controller
public class PickerController {
    @GetMapping("")
    public ModelAndView view(ModelAndView mav) {
        mav.setViewName("picker");
        mav.addObject("img", RiotAPIConstants.IMAGE);
        return mav;
    }
}
