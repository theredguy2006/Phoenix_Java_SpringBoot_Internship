package com.red.userexceptions.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorMessageDto {
    private String message;
    private String details;
    private LocalDateTime timestamp;
}
