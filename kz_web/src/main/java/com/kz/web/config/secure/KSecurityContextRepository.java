package com.kz.web.config.secure;

import com.kz.web.context.TokenAuthUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.DeferredSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class KSecurityContextRepository implements SecurityContextRepository {
    @Override
    @Deprecated
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        return null;
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public DeferredSecurityContext loadDeferredContext(HttpServletRequest request) {
        Supplier<SecurityContext> supplier = () -> readSecurityContextFromToken(request);

        return null;
    }

    private SecurityContext readSecurityContextFromToken(HttpServletRequest request) {
        Authentication kzAuth = TokenAuthUtil.tokenToAuth(request.getHeader("KZ_AUTH"));
        return null;
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        return false;
    }
}
