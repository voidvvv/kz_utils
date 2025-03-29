package com.kz.web.config.secure.context;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class KAuthFilter extends OncePerRequestFilter {
    TokenAuthUtil tokenAuthUtil;

    public KAuthFilter(TokenAuthUtil tokenAuthUtil) {
        this.tokenAuthUtil = tokenAuthUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        KAuthentication convert = tokenAuthUtil.convert(request);
        if (convert != null) {
            SecurityContextHolder.getContextHolderStrategy().getContext().setAuthentication(convert);
        }
        filterChain.doFilter(request, response);
    }
}
