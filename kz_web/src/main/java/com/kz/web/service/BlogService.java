package com.kz.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kz.web.blog.BlogFile;
import com.kz.web.blog.FileRepository;
import com.kz.web.converter.BlogConverter;
import com.kz.web.dto.KzBlogDTO;
import com.kz.web.dto.KzBlogletDTO;
import com.kz.web.entity.KzBlog;
import com.kz.web.mapper.KzBlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlogService {
    @Autowired
    private KzBlogMapper kzBlogMapper;

    @Autowired
    private BlogConverter blogConverter;

    @Autowired
    private FileRepository fileRepository;

    public List<KzBlogletDTO> findAllBlog(int start, int pageSize) {
        List<KzBlog> kzBlogs = kzBlogMapper
                .selectList(
                        new Page<>(start, pageSize), null
                );

        return blogConverter.convertListEntityToDto(kzBlogs);
    }

    public KzBlogDTO findBlogById(int id) {
        KzBlog kzBlog = kzBlogMapper.selectById(id);
        if (kzBlog == null) {
            throw new RuntimeException("Blog not found");
        }
        KzBlogDTO dto = blogConverter.convertEntityToDto(kzBlog);
        byte[] bytes = fileRepository.readFile(kzBlog.getFileUrl());
        dto.setContent(new String(bytes));
        return dto;
    }

    public KzBlog findBlogByTitle (String title) {
        KzBlog kzBlog = kzBlogMapper.selectOne(
                new QueryWrapper<KzBlog>().eq("title", title)
        );
        return kzBlog;
    }

    public KzBlog addBlog(KzBlogDTO kzBlog) {
        KzBlog entity = new KzBlog();
        blogConverter.convertDtoToEntity(kzBlog, entity);
        // save blog file
        BlogFile blogFile = fileRepository.saveBlogFile(kzBlog);
        entity.setFileFormat(blogFile.getFileFormat());
        entity.setFileUrl(blogFile.getFilePath());
        entity.setFileMethod(blogFile.getFileMethod());

        kzBlogMapper.insert(entity);
        return entity;
    }

    public List<KzBlogletDTO> findAllBloglet(int page, int pageSize) {
        List<KzBlog> kzBlogs = kzBlogMapper
                .selectList(
                        new Page<>(page, pageSize), new QueryWrapper<>()
                );

        return blogConverter.convertListEntityToDto(kzBlogs);
    }


}
