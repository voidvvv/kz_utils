package com.kz.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DemoController {
//    @GetMapping({"/",""})
//    public String index() {
//        return "index";
//    }
    @GetMapping("/hello")
    public String demo() {
        return "demo";
    }

    @GetMapping("/home")
    public String home () {
        return "home";
    }
}
