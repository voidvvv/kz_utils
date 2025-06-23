package com.kz.web.converter;

import com.kz.web.Util;
import com.kz.web.dto.KzBlogDTO;
import com.kz.web.dto.KzBlogletDTO;
import com.kz.web.entity.KzBlog;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BlogConverter {

    public KzBlog convertDtoToEntity (KzBlogDTO dto, KzBlog entity) {
        String currentUser = Util.fetchCurrentUserId();
        entity.setTitle(dto.getTitle());
        if ( dto.getDescription() != null && !dto.getDescription().isEmpty()) {
            entity.setSimpleDescription(dto.getDescription());
        } else {
            entity.setSimpleDescription(dto.getTitle());
        }
        entity.setFileFormat(dto.getFileFormat());
//        entity.setFileUrl(dto.getFileUrl());
        entity.setFileMethod("LOCAL");
        entity.setAuthorUserId(currentUser);
        entity.setExcerpt(dto.getExcerpt());
        return entity;
    }



    public List<KzBlogletDTO> convertListEntityToDto(List<KzBlog> kzBlogs) {
        List<KzBlogletDTO> list = new ArrayList<>();
        kzBlogs.forEach(e -> {
            KzBlogletDTO dto = new KzBlogletDTO();
            dto.setId(e.getId());
            dto.setTitle(e.getTitle());
            dto.setDescription(e.getSimpleDescription());
            dto.setFileFormat(e.getFileFormat());
            dto.setAuthor(e.getAuthorUserId());
            dto.setExcerpt(e.getExcerpt());
            dto.setCreateDate(e.getCreateTime());
            dto.setUpdateDate(e.getUpdateTime());
//            dto.setTags(e.getTags());
//            dto.setCategories(e.getCategories());
            list.add(dto);
        });
        return list;
    }

    public KzBlogDTO convertEntityToDto(KzBlog kzBlog) {
        KzBlogDTO dto = new KzBlogDTO();
        dto.setId(kzBlog.getId());
        dto.setTitle(kzBlog.getTitle());
        dto.setDescription(kzBlog.getSimpleDescription());
        dto.setFileFormat(kzBlog.getFileFormat());
//        dto.setFileUrl(kzBlog.getFileUrl());
        dto.setAuthor(kzBlog.getAuthorUserId());
        dto.setExcerpt(kzBlog.getExcerpt());
//        dto.setTags(kzBlog.getTags());
//        dto.setCategories(kzBlog.getCategories());
        dto.setCreateDate(kzBlog.getCreateTime());
        dto.setUpdateDate(kzBlog.getUpdateTime());
        return dto;
    }
}
