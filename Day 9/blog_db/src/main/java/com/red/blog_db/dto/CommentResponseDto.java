package com.red.blog_db.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {

    private Long commentId;
    private String commentBody;
    private LocalDateTime commentTime;

    private Long postId;
    private String postTitle;

    private Long userId;
    private String userName;
}
//The Goal here is to just give all comments but not post details. Just that this comment is on this post via this user
