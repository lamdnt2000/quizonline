package com.quizonline.group8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {
    @RequestMapping("/")
    public ModelAndView index(){
        return new ModelAndView("index");
    }
}
