package com.kz.web.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class KzBlogCommentDTO {
    private Integer id;

    private Integer blogId;

    private Integer parentCommentId;

    private String comment;

    private List<KzBlogCommentDTO> children = new ArrayList<>();

    private Long createTime;

    private String createBy;

    private Long updateTime;

    private String updateBy;
}
