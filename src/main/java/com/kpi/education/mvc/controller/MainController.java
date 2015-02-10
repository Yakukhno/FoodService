package com.kpi.education.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping("/main")
    public ModelAndView handle() {
        ModelAndView model = new ModelAndView("index");

        return model;
    }

}
