package com.kz.web.config.secure.context.filters;

import com.kz.web.config.secure.context.base.KAuthentication;
import com.kz.web.config.secure.context.providers.KAuthenticationProvider;
import com.kz.web.config.secure.context.TokenAuthUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class KAuthFilter extends OncePerRequestFilter {
    TokenAuthUtil tokenAuthUtil;

    private AuthenticationManager authenticationManager;

    public KAuthFilter(TokenAuthUtil tokenAuthUtil, AuthenticationManager authenticationManager) {
        this.tokenAuthUtil = tokenAuthUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        KAuthentication convert = tokenAuthUtil.convert(request);
        if (convert != null) {
            Authentication auth = authenticationManager.authenticate(convert);
            SecurityContextHolder.getContextHolderStrategy().getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }
}
