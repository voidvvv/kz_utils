package com.kz.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaTestController {

    @Autowired
    private KafkaTemplate<String, String> template;

    @PostMapping("/send")
    public String send (@RequestParam("key")  String key,@RequestParam("value") String value) {
        template.send("test-topic", key, value);
        return "yes";
    }
}
