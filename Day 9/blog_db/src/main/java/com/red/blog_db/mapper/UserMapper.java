package com.red.blog_db.mapper;

import com.red.blog_db.dto.CreateUserRequestDTO;
import com.red.blog_db.dto.UpdateUserRequestDTO;
import com.red.blog_db.dto.UserResponseDTO;
import com.red.blog_db.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toDTO(UserEntity user) {
        if (user == null) return null;

        UserResponseDTO dto = new UserResponseDTO();
        dto.setUserId(user.getUserId());
        dto.setUserName(user.getUserName());
        dto.setEmailId(user.getEmailId());
        dto.setCreatedAt(user.getCreatedAt());

        return dto;
    }

    public void updateEntity(UserEntity user, UpdateUserRequestDTO dto) {

        if (dto.getUserName() != null) {
            user.setUserName(dto.getUserName());
        }

        if (dto.getEmailId() != null) {
            user.setEmailId(dto.getEmailId());
        }

        if (dto.getUserPwd() != null) {
            user.setUserPwd(dto.getUserPwd());
        }
    }


    public UserEntity toEntity(CreateUserRequestDTO dto) {

        UserEntity user = new UserEntity();
        user.setUserName(dto.getUserName());
        user.setEmailId(dto.getEmailId());
        user.setUserPwd(dto.getUserPwd());

        return user;
    }
}