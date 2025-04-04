package com.kz.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kz.web.entity.KzUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface KzUserMapper extends BaseMapper<KzUser> {

    @Select("SELECT * FROM kz_user")
    KzUser findByUsername(String username);


}
