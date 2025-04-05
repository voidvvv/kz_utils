package com.kz.web.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ForumController {

    @RequestMapping("/md")
    public String md() {
        return "md.html";
    }

    @RequestMapping("/forum")
    public String forum() {
        return "forum";
    }
}
