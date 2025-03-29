package com.kz.web.config.secure.context.base;

import org.springframework.security.core.GrantedAuthority;

public class KAuthority implements GrantedAuthority {
    String name;

    public KAuthority(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
