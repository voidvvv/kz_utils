package com.kz.auth.context;

import com.kz.auth.context.base.KAuthentication;
import com.kz.auth.context.base.KAuthority;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class TokenAuthUtil implements AuthenticationConverter {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TokenAuthUtil.class);

    public String authToToken(Authentication auth) {
        Object principal = auth.getPrincipal();
        if (KAuthentication.class.isAssignableFrom(auth.getClass())) {
            String jws = Jwts.builder()
                    .subject(auth.getName())
                    .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                    .claims()
                    .add("authList", auth.getAuthorities())
                    .and()
                    .header()
                    .add("username", principal)
                    .add("auth", auth.isAuthenticated())
                    .and()
                    .signWith(RSAUtil.privateKey)
                    .compact();
            return jws;
        } else {
            String userName = null;
            if (UserDetails.class.isAssignableFrom(principal.getClass())) {
                userName = ((UserDetails) principal).getUsername();
            }
            String jws = Jwts.builder()
                    .subject(auth.getName())
                    .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                    .claims()
                    .add("authList", auth.getAuthorities())
                    .and()
                    .header()
                    .add("username", userName)
                    .add("auth", auth.isAuthenticated())
                    .and()
                    .signWith(RSAUtil.privateKey)
                    .compact();
            return jws;
        }

    }

    public KAuthentication tokenToAuth(String token) {
        try {
            if (token == null) {
                return null;
            }
            Jwt<?, ?> jwt = Jwts.parser()
                    .verifyWith(RSAUtil.publicKey)
                    .build().parse(token);

            Header header = jwt.getHeader();

            Claims payload = (Claims) jwt.getPayload();
            Date expiration = payload.getExpiration();

            KAuthentication auth = new KAuthentication();
            auth.setUserName((String) header.get("username"));
//            auth.setAuthState((Boolean) header.get("auth"));
            auth.setExpireDate(expiration);
            payload.get("authList", List.class)
                    .forEach(
                            (g) -> {
                                auth.addAuthority(new KAuthority(((Map) g).get("authority").toString()));
                            }
                    );
            return auth;
        } catch (Exception e) {
            log.error("tokenToAuth error: {}", e);
            return null;
        }
    }

    public static void main(String[] args) {
        TokenAuthUtil tokenAuthUtil = new TokenAuthUtil();
        KAuthentication demoAuth = new KAuthentication();
        demoAuth.setUserName("demo");
        demoAuth.setPassword("demo");
        demoAuth.setAuthState(true);
        demoAuth.addAuthority(new KAuthority("ROLE_USER"));
        String token = tokenAuthUtil.authToToken(demoAuth);
        log.info("token: {}", token);
        Authentication auth = tokenAuthUtil.tokenToAuth(token);
        log.info("auth: {}", auth);

    }

    @Override
    public KAuthentication convert(HttpServletRequest request) {
        String jtw = request.getHeader("KZ_AUTH");
        return this.tokenToAuth(jtw);
    }
}
