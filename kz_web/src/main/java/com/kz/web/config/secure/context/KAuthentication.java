package com.kz.web.config.secure.context;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class KAuthentication implements Authentication {
    private String userName;
    private String password;

    private Date expireDate;

    List<GrantedAuthority> authorities = new ArrayList<>();

    private boolean authState;

    public void addAuthority(GrantedAuthority authority) {
        authorities.add(authority);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getCredentials() {
        return password;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public String getPrincipal() {
        return userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isAuthState() {
        return authState;
    }

    public void setAuthState(boolean authState) {
        this.authState = authState;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean isAuthenticated() {
        return authState;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        authState = isAuthenticated;
    }

    @Override
    public String getName() {
        return "normal_user_auth";
    }
}
