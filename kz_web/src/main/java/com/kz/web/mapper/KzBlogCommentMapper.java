package com.kz.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kz.web.entity.KzBlogComment;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface KzBlogCommentMapper extends BaseMapper<KzBlogComment> {

    @Select("SELECT * FROM kz_blog_comment WHERE blog_id = #{blogId}")
    List<KzBlogComment> findAllByBlogId(int blogId);

    @Select({
        "<script>",
        "SELECT * FROM kz_blog_comment WHERE parent_comment_id IN",
        "<foreach item='item' index='index' collection='parentIds' open='(' separator=',' close=')'>",
        "#{item}",
        "</foreach>",
        "</script>"
    })
    List<KzBlogComment> findAllChildrenComments(List<Integer> parentIds);
}
