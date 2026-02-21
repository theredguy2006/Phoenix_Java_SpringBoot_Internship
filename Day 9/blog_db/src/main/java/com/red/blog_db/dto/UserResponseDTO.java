package com.red.blog_db.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDTO {

    private Long userId;
    private String userName;
    private String emailId;
    private LocalDateTime createdAt;

}