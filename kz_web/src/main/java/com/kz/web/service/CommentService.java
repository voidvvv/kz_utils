package com.kz.web.service;

import com.kz.web.dto.KzBlogCommentDTO;
import com.kz.web.entity.KzBlogComment;
import com.kz.web.mapper.KzBlogCommentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class CommentService {

    @Autowired
    private KzBlogCommentMapper commentMapper;

    public List<KzBlogCommentDTO> findAllByBlogId(int blog) {
        log.trace("Fetching all comments for blog id: {}", blog);
        List<KzBlogComment> allComments = commentMapper.findAllByBlogId(blog);
        if (allComments == null || allComments.isEmpty()) {
            log.info("No comments found for blog id: {}", blog);
            return new ArrayList<>();
        }
        List<Integer> parentIds = allComments.stream().map(KzBlogComment::getBlogId).toList();
        List<KzBlogComment> allComments2 = commentMapper.findAllChildrenComments(parentIds);
        List<KzBlogCommentDTO> result = new ArrayList<>();
        convert(allComments, allComments2, result);
        return result;
    }

    void convert(List<KzBlogComment> parents, List<KzBlogComment> entities, List<KzBlogCommentDTO> dtoList) {
        Map<Integer, KzBlogCommentDTO> blogMap = new HashMap<>();
        for (KzBlogComment entity : parents) {
            Integer id = entity.getId();
            KzBlogCommentDTO entityParent = new KzBlogCommentDTO() {{
                setId(id);
                setBlogId(entity.getBlogId());
                setParentCommentId(entity.getParentCommentId());
                setComment(entity.getComment());
                setCreateTime(entity.getCreateTime());
                setCreateBy(entity.getCreateBy());
                setUpdateTime(entity.getUpdateTime());
                setUpdateBy(entity.getUpdateBy());
            }};
            blogMap.put(id, entityParent);
            dtoList.add(entityParent);
        }

        entities.forEach(entity -> {
            Integer parentId = entity.getParentCommentId();
            if (blogMap.containsKey(parentId)) {
                KzBlogCommentDTO parentDto = blogMap.get(parentId);
                KzBlogCommentDTO childDto = new KzBlogCommentDTO() {{
                    setId(entity.getId());
                    setBlogId(entity.getBlogId());
                    setParentCommentId(entity.getParentCommentId());
                    setComment(entity.getComment());
                    setCreateTime(entity.getCreateTime());
                    setCreateBy(entity.getCreateBy());
                    setUpdateTime(entity.getUpdateTime());
                    setUpdateBy(entity.getUpdateBy());
                }};
                parentDto.getChildren().add(childDto);
            }
        });
    }
}
