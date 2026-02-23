package com.red.blog_db.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostResponseDto {

    private Long postId;
    private String postTitle;
    private String postBody;
    private LocalDateTime postTime;

    private Long userId;
    private String userName;

    private List<CommentResponseDto> comments;
}