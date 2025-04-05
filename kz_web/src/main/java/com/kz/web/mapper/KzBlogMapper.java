package com.kz.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kz.web.entity.KzBlog;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface KzBlogMapper extends BaseMapper<KzBlog> {

    /**
     * 分页查询所有博客
     * @param start 起始页
     * @param pageSize 每页大小
     * @return 博客列表
     */
    @Select("SELECT * FROM kz_blog LIMIT #{start}, #{pageSize}")
    public List<KzBlog> findAllBlog(int start, int pageSize);
}
