package com.kz.web.controller;

import com.kz.web.blog.PageInfo;
import com.kz.web.dto.KzBlogDTO;
import com.kz.web.dto.KzBlogletDTO;
import com.kz.web.dto.ResponseDTO;
import com.kz.web.entity.KzBlog;
import com.kz.web.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
@Slf4j
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/list")
    public ResponseDTO<List<KzBlogletDTO>> getBlogList(PageInfo pageInfo) {
        log.info("get blog list, page: {}, pageSize: {}", pageInfo.getPage(), pageInfo.getPageSize());
        List<KzBlogletDTO> allBlog = blogService.findAllBloglet(pageInfo.getPage(), pageInfo.getPageSize());
        return ResponseDTO.ok(allBlog);
    }

    @PostMapping("/add")
    public ResponseDTO<KzBlog> addBlog(@RequestBody KzBlogDTO kzBlog) {
        log.info("add blog, title: {}", kzBlog.getTitle());
        KzBlog addedBlog = blogService.addBlog(kzBlog);
        return ResponseDTO.ok(addedBlog);
    }
}