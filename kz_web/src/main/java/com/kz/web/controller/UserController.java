package com.kz.web.controller;

import com.kz.auth.context.TokenAuthUtil;
import com.kz.web.config.secure.context.users.UserService;
import com.kz.web.dto.KzUserInfo;
import com.kz.web.dto.RegisterUserDTO;
import com.kz.web.dto.ResponseDTO;
import com.kz.web.entity.KzAccount;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/login")
    public String login(HttpServletRequest request) {
        return "";
    }

    @PostMapping("/admin/register")
    public ResponseDTO register(@RequestBody RegisterUserDTO request) {
        userService.register(request);
        return ResponseDTO.ok();
    }

    @RequestMapping("/user/all")
    public ResponseDTO<List<KzAccount>> getUserList() {
        return null;
    }

    @RequestMapping("/user/current")
    public ResponseDTO<KzUserInfo> currentUserInfo() {
        return ResponseDTO.ok(userService.currentUserInfo());
    }

}
