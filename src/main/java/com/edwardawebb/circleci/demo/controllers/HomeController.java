package com.edwardawebb.circleci.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Controller
public class HomeController {
    public static final String ABOUT_VIEW = "about";
    
    Counter visitCounter;

    public HomeController(MeterRegistry registry) {
        visitCounter = Counter.builder("visit_counter")
            .description("Number of visits to the site")
            .register(registry);
    }

    @RequestMapping("/about")
    public String about(Model model) {
        visitCounter.increment();
        return ABOUT_VIEW;
    }


}
