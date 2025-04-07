package com.kz.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kz.web.entity.KzAccount;
import org.apache.ibatis.annotations.Select;


public interface KzAccountMapper extends BaseMapper<KzAccount> {

    @Select("SELECT * FROM kz_account where username = #{username}")
    KzAccount findByUsername(String username);


}
