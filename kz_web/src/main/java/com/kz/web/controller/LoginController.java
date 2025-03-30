package com.kz.web.controller;

import com.kz.auth.context.TokenAuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenAuthUtil tokenAuthUtil;

    @RequestMapping("/login")
    public String login() {
        UsernamePasswordAuthenticationToken auth = UsernamePasswordAuthenticationToken.unauthenticated("user","123456");
        Authentication result = authenticationManager.authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(result);
        return tokenAuthUtil.authToToken(result);

    }
}
