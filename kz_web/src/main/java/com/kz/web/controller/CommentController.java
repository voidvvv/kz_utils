package com.kz.web.controller;

import com.kz.web.dto.KzBlogCommentDTO;
import com.kz.web.dto.ResponseDTO;
import com.kz.web.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping("/blog/{blog}")
    public ResponseDTO<List<KzBlogCommentDTO>> allComments (@PathVariable("blog") int blog) {
        List<KzBlogCommentDTO> list = commentService.findAllByBlogId(blog);
        return ResponseDTO.ok(list);
    }
}
