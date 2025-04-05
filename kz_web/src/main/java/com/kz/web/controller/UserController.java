package com.kz.web.controller;

import com.kz.auth.context.TokenAuthUtil;
import com.kz.web.config.secure.context.users.UserService;
import com.kz.web.entity.KzUser;
import com.kz.web.mapper.KzUserMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    TokenAuthUtil tokenAuthUtil;


    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        return "";
    }
    @RequestMapping("/user/all")
    public List<KzUser> getUserList() {
        return null;
    }

}
