package com.kz.web.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.kz.web.Util;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
public class MyBatisPlusHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        long currentTime = System.currentTimeMillis();
        String currentUser = Util.fetchCurrentUserId();
        this.setFieldValByName("createBy", currentUser, metaObject);
        this.setFieldValByName("createTime", currentTime, metaObject);
        this.setFieldValByName("updateBy", currentUser, metaObject);
        this.setFieldValByName("updateTime", currentTime, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        long currentTime = System.currentTimeMillis();
        String currentUser = Util.fetchCurrentUserId();

        this.setFieldValByName("updateBy", currentUser, metaObject);
        this.setFieldValByName("updateTime", currentTime, metaObject);
    }
}
