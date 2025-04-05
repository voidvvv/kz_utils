package com.kz.web.controller;

import com.kz.web.blog.PageInfo;
import com.kz.web.dto.KzBlogDTO;
import com.kz.web.dto.ResponseDTO;
import com.kz.web.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blog")
@Slf4j
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/list")
    public ResponseDTO<List<KzBlogDTO>> getBlogList(PageInfo pageInfo) {
        log.info("get blog list, page: {}, pageSize: {}", pageInfo.getPage(), pageInfo.getPageSize());
        List<KzBlogDTO> allBlog = blogService.findAllBlog(pageInfo.getPage(), pageInfo.getPageSize());
        return ResponseDTO.ok(allBlog);
    }
}
