package com.red.userexceptions.mapper;

import com.red.userexceptions.dto.UserCreationDto;
import com.red.userexceptions.dto.UserRequestDto;
import com.red.userexceptions.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserCreationDto toCreationDto(UserEntity user);

    UserRequestDto toRequestDto(UserEntity user);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    UserEntity toEntity(UserCreationDto dto);
}


//public class UserMapper {
//    public UserCreationDto creationDto(UserEntity user) {
//        if (user == null) return null;
//
//        UserCreationDto dto = new UserCreationDto();
//        dto.setUserName(user.getUserName());
//        dto.setEmailId(user.getEmailId());
//        dto.setCreatedAt(user.getCreatedAt());
//
//        return dto;
//
//    }
//
//    public UserRequestDto requestDto(UserEntity user) {
//        if (user == null) return null;
//
//        UserRequestDto dto = new UserRequestDto();
//        dto.setUserId(user.getUserId());
//        dto.setUserName(user.getUserName());
//        dto.setEmailId(user.getEmailId());
//        return dto;
//    }
//
//}