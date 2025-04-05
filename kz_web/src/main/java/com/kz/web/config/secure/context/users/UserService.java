package com.kz.web.config.secure.context.users;

import com.kz.web.dto.RegisterUserDTO;
import com.kz.web.entity.KzUser;
import com.kz.web.mapper.KzUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private KzUserMapper kzUserMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        KzUser foundUser = kzUserMapper.findByUsername(username);
        if (foundUser == null || illegalUser(foundUser)) {
            throw new UsernameNotFoundException("User not found for username: " + username);
        }
        // compose authorities
        findAllAuthorities(foundUser);
        return foundUser;
    }

    public void findAllAuthorities(KzUser foundUser) {

    }

    private boolean illegalUser(KzUser foundUser) {
        return false;
    }

    public void register(RegisterUserDTO request) {
        Date date = new Date();
        KzUser user = new KzUser();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setLockSign(0);
        user.setVersion(0);
        user.setCreateTime(date.getTime());
        user.setUpdateTime(date.getTime());
        user.setCreateBy("system");
        user.setUpdateBy("system");
        kzUserMapper.insert(user);
        log.info("user {} register success", request.getUsername());
    }
}
