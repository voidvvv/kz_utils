package com.kz.web.config.secure.context;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class KAuthFilter extends OncePerRequestFilter {
    TokenAuthUtil tokenAuthUtil;
    KAuthenticationProvider kAuthenticationProvider;

    public KAuthFilter(TokenAuthUtil tokenAuthUtil, KAuthenticationProvider kAuthenticationProvider) {
        this.tokenAuthUtil = tokenAuthUtil;
        this.kAuthenticationProvider = kAuthenticationProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        KAuthentication convert = tokenAuthUtil.convert(request);
        if (convert != null) {
            Authentication auth = kAuthenticationProvider.authenticate(convert);
            SecurityContextHolder.getContextHolderStrategy().getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }
}
