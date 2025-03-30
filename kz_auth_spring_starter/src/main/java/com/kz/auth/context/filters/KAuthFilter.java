package com.kz.auth.context.filters;

import com.kz.auth.context.TokenAuthUtil;
import com.kz.auth.context.base.KAuthentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
