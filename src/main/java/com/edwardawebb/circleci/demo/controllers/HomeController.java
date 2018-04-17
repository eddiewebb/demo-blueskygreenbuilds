package com.edwardawebb.circleci.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    public static final String ABOUT_VIEW = "about";
    public static final String CONTACT_VIEW = "contact";

    @RequestMapping("/about")
    public String about(Model model) {
        return ABOUT_VIEW;
    }


}
