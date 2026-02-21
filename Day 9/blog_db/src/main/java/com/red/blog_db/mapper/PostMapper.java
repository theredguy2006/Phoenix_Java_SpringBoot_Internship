package com.red.blog_db.mapper;

import com.red.blog_db.dto.CommentResponseDto;
import com.red.blog_db.dto.PostResponseDto;
import com.red.blog_db.entity.CommentEntity;
import com.red.blog_db.entity.PostEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostMapper {

    public PostResponseDto toDTO(PostEntity post) {

        PostResponseDto dto = new PostResponseDto();

        dto.setPostId(post.getPostId());
        dto.setPostTitle(post.getPostTitle());
        dto.setPostBody(post.getPostBody());
        dto.setPostTime(post.getPostTime());

        dto.setUserId(post.getUserEntity().getUserId());
        dto.setUserName(post.getUserEntity().getUserName());

        // 🔹 Map Comments
        List<CommentResponseDto> commentDtos =
                post.getCommentEntityList()
                        .stream()
                        .map(this::mapComment)
                        .collect(Collectors.toList());

        dto.setComments(commentDtos);

        return dto;
    }

    private CommentResponseDto mapComment(CommentEntity comment) {

        CommentResponseDto dto = new CommentResponseDto();

        dto.setCommentId(comment.getCommentId());
        dto.setCommentBody(comment.getCommentBody());
        dto.setCommentTime(comment.getCommentTime());

        dto.setUserId(comment.getUserEntity().getUserId());
        dto.setUserName(comment.getUserEntity().getUserName());

        return dto;
    }
}