package com.example.picker.controller.kled;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KledController {
    @GetMapping("/kled")
    public ModelAndView kled(ModelAndView mav) {
        mav.setViewName("kled");
        return mav;
    }
}
