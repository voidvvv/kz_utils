package com.kz.web.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

@Data
@TableName("kz_blog_comment")
public class KzBlogComment {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer blogId;

    private Integer parentCommentId;

    private String comment;

    private Long createTime;

    private String createBy;

    private Long updateTime;

    private String updateBy;
}