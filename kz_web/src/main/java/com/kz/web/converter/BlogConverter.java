package com.kz.web.converter;

import com.kz.web.dto.KzBlogDTO;
import com.kz.web.entity.KzBlog;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BlogConverter {

    public KzBlog convertDtoToEntity (KzBlogDTO dto) {
        String currentUser = fetchCurrenmtUserId();
        KzBlog entity = new KzBlog();
        entity.setTitle(dto.getTitle());
        entity.setSimpleDescription(dto.getDescription());
        entity.setFileFormat(dto.getFileFormat());
//        entity.setFileUrl(dto.getFileUrl());
        entity.setFileMethod("LOCAL");
        entity.setAuthorUserId(currentUser);
        entity.setCreateTime(System.currentTimeMillis());
        entity.setUpdateBy(currentUser);
        entity.setUpdateTime(System.currentTimeMillis());
        return entity;
    }

    private String fetchCurrenmtUserId() {
        return (String) SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication().getPrincipal();

    }

    public List<KzBlogDTO> convertListEntityToDto(List<KzBlog> kzBlogs) {
        List<KzBlogDTO> list = new ArrayList<>();
        kzBlogs.forEach(e -> {
            KzBlogDTO dto = new KzBlogDTO();
            dto.setId(e.getId());
            dto.setTitle(e.getTitle());
            dto.setDescription(e.getSimpleDescription());
            dto.setFileFormat(e.getFileFormat());
//            dto.setTags(e.getTags());
//            dto.setCategories(e.getCategories());
            list.add(dto);
        });
        return list;
    }
}
