package com.kz.web.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

@Data
@TableName("kz_user")
public class KzUser {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer kzAccountId;

    private String nickname;

    private String email;

    private String phone;

    private String avatar;
}