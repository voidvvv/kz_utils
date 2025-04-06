package com.kz.web.config.secure.context.users;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kz.web.config.secure.context.exceptions.AlreadyExistingUser;
import com.kz.web.dto.KzUserInfoDTO;
import com.kz.web.dto.RegisterUserDTO;
import com.kz.web.entity.KzAccount;
import com.kz.web.entity.KzUser;
import com.kz.web.mapper.KzAccountMapper;
import com.kz.web.mapper.KzUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private KzAccountMapper kzAccountMapper;
    @Autowired
    private KzUserMapper kzUserMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        KzAccount foundUser = kzAccountMapper.findByUsername(username);
        if (foundUser == null || illegalUser(foundUser)) {
            throw new UsernameNotFoundException("User not found for username: " + username);
        }
        // compose authorities
        findAllAuthorities(foundUser);
        return foundUser;
    }

    public void findAllAuthorities(KzAccount foundUser) {

    }

    private boolean illegalUser(KzAccount foundUser) {
        return false;
    }

    public void register(RegisterUserDTO request) {
        String username = request.getUsername();
        // besure username is unique
        KzAccount foundUser = kzAccountMapper.findByUsername(username);
        if (foundUser != null) {
            log.error("user {} already exists", username);
            throw new AlreadyExistingUser("user " + username + " already exists");
        }
        saveNewUser(request);
        log.info("user {} register success", username);
    }

    private void saveNewUser(RegisterUserDTO request) {
        Date date = new Date();
        KzAccount user = new KzAccount();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setLockSign(0);
        user.setVersion(0);
        user.setCreateTime(date.getTime());
        user.setUpdateTime(date.getTime());
        user.setCreateBy("system");
        user.setUpdateBy("system");
        kzAccountMapper.insert(user);
    }

    public KzUserInfoDTO currentUserInfo() {
        KzUserInfoDTO info = new KzUserInfoDTO();
        SecurityContext context = SecurityContextHolder.getContextHolderStrategy().getContext();
        Assert.notNull(context, "current user not login");
        Authentication authentication = context.getAuthentication();
        Assert.notNull(authentication, "current user not login");
        Object principal = authentication.getPrincipal();
        String userName = null;
        if (principal instanceof KzAccount user) {
            userName = user.getUsername();
        } else if (principal instanceof UserDetails user) {
            userName = user.getUsername();
        } else if (principal instanceof String) {
            userName = (String) principal;
        } else {
            throw new IllegalArgumentException("current user is illegal");
        }
        info.setUsername(userName);
        QueryWrapper<KzUser> queryWrapper = new QueryWrapper<KzUser>()
                .eq("kz_account_id", userName);
        List<KzUser> kzUsers = kzUserMapper.selectList(queryWrapper);
        Assert.notEmpty(kzUsers, "current user is illegal");
        KzUser kzUser = determineUser(kzUsers);
        info.setNickname(kzUser.getNickname());
        info.setEmail(kzUser.getEmail());
        info.setPhone(kzUser.getPhone());
        info.setAvatar(kzUser.getAvatar());
        return info;
    }

    private KzUser determineUser(List<KzUser> kzUsers) {
        return kzUsers.get(0);
    }
}
