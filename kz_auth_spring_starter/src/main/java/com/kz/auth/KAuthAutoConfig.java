package com.kz.auth;

import com.kz.auth.context.TokenAuthUtil;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration()
public class KAuthAutoConfig {

    @Bean
    public TokenAuthUtil tokenAuthUtil() {
        return new TokenAuthUtil();
    }
}
