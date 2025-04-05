package com.kz.web.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

@Data
@TableName("kz_blog")
public class KzBlog {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private String authorUserId;

    private String simpleDescription;

    private String excerpt;

    private String fileFormat;

    private String fileUrl;

    private String fileMethod;

    private Long createTime;

    private String createBy;

    private Long updateTime;

    private String updateBy;
}