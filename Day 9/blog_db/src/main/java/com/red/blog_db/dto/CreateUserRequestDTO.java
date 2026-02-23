package com.red.blog_db.dto;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequestDTO {

    @NotBlank
    @Size(min = 4, max = 30)
    private String userName;

    @NotBlank
    @Email
    private String emailId;

    @NotNull
    @Min(1000)
    @Max(9999)
    private Long userPwd;

}