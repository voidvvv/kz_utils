package com.kz.web.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//@RestController
@RequestMapping("/test")
@Controller
public class TestController {
    @Value("${my.client.id}")
    String myClientId;

    @Value("${my.client.secret}")
    String myClientSecret;

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,20,200, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20));
    @RequestMapping("/gitHubOauthRedirect")
    public String gitHubOauthRedirect (@RequestParam("code") String code, HttpServletResponse response) {
        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "https://github.com/login/oauth/access_token";


        // append params
        String url = baseUrl + "?client_id=" + myClientId + "&client_secret=" + myClientSecret + "&code=" + code;
        HttpEntity<String> entity = new HttpEntity<>(null);
        String github_response = restTemplate.postForObject(url, entity, String.class);
        // return the response
        if (github_response != null) {
            response.setHeader("github_token", "github_response");
        }
        return "redirect:/"; // redirect to home page
    }


    @GetMapping("/sse")
    @ResponseBody
    public SseEmitter testSse () {
        SseEmitter se = new SseEmitter();
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i ++) {
                    try {
                        se.send(SseEmitter.event()
                                .data("hahaha" + i)
                                .id(i + "")
                                .name("name test"));
                        Thread.sleep(200);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
                se.complete();
            }
        });
//
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("http://localhost:8080/sse-stream"))
//                .header("Accept", "text/event-stream") // 必需头
//                .build();
//
//        client.sendAsync(request, HttpResponse.BodyHandlers.ofLines())
//                .thenAccept(response -> {
//                    response.body().forEach(line -> {
//                        if (line.startsWith("data:")) {
//                            System.out.println("收到事件: " + line.substring(5).trim());
//                        }
//                    });
//                })
//                .join();
        return se;
    }

}
