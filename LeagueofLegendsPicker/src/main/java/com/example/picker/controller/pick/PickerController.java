package com.example.picker.controller.pick;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.picker.common.constant.RiotAPIConstants;

@Controller
public class PickerController {
    @GetMapping("pick")
    public ModelAndView view(ModelAndView mav) {
        mav.setViewName("picker");
        mav.addObject("img", RiotAPIConstants.IMAGE);
        return mav;
    }
}
