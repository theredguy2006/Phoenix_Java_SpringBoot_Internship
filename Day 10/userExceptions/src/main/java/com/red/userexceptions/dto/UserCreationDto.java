package com.red.userexceptions.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserCreationDto {
    private Long userId;
    private String userName;
    private String emailId;
    private LocalDateTime createdAt;
}
