package com.kz.web.config.secure.context;

import com.kz.auth.context.TokenAuthUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private TokenAuthUtil tokenAuthUtil;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Custom logic after successful authentication
        // For example, you can redirect to a specific URL or send a success response
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(tokenAuthUtil.authToToken(authentication));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
