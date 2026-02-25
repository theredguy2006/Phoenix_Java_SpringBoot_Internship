package com.red.userexceptions.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserCreationDto {
    @NotBlank(message = "Username is required")
    private String userName;

    @Email(message = "Invalid email format")
    private String emailId;

    @NotBlank(message = "Password cannot be empty")
    private String userPwd;
}
