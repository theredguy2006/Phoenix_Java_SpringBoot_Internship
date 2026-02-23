package com.red.blog_db.mapper;

import com.red.blog_db.dto.CommentResponseDto;
import com.red.blog_db.entity.CommentEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentResponseDto toDTO(CommentEntity comment) {

        CommentResponseDto dto = new CommentResponseDto();

        dto.setCommentId(comment.getCommentId());
        dto.setCommentBody(comment.getCommentBody());
        dto.setCommentTime(comment.getCommentTime());

        dto.setPostId(comment.getPostEntity().getPostId());
        dto.setPostTitle(comment.getPostEntity().getPostTitle());

        dto.setUserId(comment.getUserEntity().getUserId());
        dto.setUserName(comment.getUserEntity().getUserName());

        return dto;
    }
}
