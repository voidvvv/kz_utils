package com.kz.web.controller;

import com.kz.auth.context.TokenAuthUtil;
import com.kz.web.entity.KzUser;
import com.kz.web.mapper.KzUserMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    TokenAuthUtil tokenAuthUtil;

    @Autowired
    private KzUserMapper kzUserMapper;

    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        UsernamePasswordAuthenticationToken auth = UsernamePasswordAuthenticationToken.unauthenticated("user","123456");
        Authentication result = authenticationManager.authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(result);
        return tokenAuthUtil.authToToken(result);
    }
    @RequestMapping("/user/all")
    public List<KzUser> getUserList() {
        return kzUserMapper.selectList(null);
    }

}
