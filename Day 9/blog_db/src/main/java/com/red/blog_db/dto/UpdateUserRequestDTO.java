package com.red.blog_db.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequestDTO {
    @Size(min = 4, max = 30)
    private String userName;

    @Email
    private String emailId;

    @Min(1000)
    @Max(9999)
    private Long userPwd;
}
