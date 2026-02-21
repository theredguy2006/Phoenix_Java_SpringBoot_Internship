package com.red.blog_db.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostDto {
    @NotBlank
    @Size(min = 20, max = 70)
    private String postTitle;

    @NotBlank
    @Size(min = 20, max = 200)
    private String postBody;

    @NotNull
    private Long userId;
}
