package com.kz.web.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;

//@Component
public class SecurityIssue {
    @Autowired
    private AuthenticationManager authenticationManager;
}
