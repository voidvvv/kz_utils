package com.kz.web.test;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class SimpleFilter extends OncePerRequestFilter {
    AuthenticationManager authenticationManager;

    public SimpleFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        System.out.println("SimpleFilter.doFilterInternal");
        MyAuthentication myAuthentication = new MyAuthentication();
        if (this.authenticationManager != null) {
            this.authenticationManager.authenticate(myAuthentication);
        }
        filterChain.doFilter(request, response);
    }
}
