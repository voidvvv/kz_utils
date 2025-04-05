package com.kz.web.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.kz.auth.context.base.KAuthority;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
@TableName("kz_account")
@Getter
@Setter
public class KzAccount implements UserDetails {

    @TableId(value = "id", type = IdType.AUTO)

    private Integer id;

    private String username;

    private String password;

    private List<KAuthority> authorities;

    private Integer lockSign;

    private Integer version;

    private Long createTime;

    private Long updateTime;

    private String createBy;

    private String updateBy;

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.lockSign == 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}