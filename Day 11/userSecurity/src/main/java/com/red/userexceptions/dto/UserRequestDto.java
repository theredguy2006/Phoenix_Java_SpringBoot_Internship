package com.red.userexceptions.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

    private Long userId;
    private String userName;
    private String emailId;

}
