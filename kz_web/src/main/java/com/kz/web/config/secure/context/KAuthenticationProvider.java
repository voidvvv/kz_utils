package com.kz.web.config.secure.context;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Date;

public class KAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        KAuthentication kAuth = (KAuthentication) authentication;
        Date expireDate = kAuth.getExpireDate();
        if (expireDate == null || expireDate.before(new Date())) {
            kAuth.setAuthenticated(false);
        } else {
            kAuth.setAuthenticated(true);
        }
        return kAuth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return KAuthentication.class.isAssignableFrom(authentication);
    }
}
