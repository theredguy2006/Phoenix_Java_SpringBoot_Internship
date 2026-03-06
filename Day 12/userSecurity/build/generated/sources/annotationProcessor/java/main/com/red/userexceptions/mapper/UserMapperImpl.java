package com.red.userexceptions.mapper;

import com.red.userexceptions.dto.UserCreationDto;
import com.red.userexceptions.dto.UserRequestDto;
import com.red.userexceptions.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-03T11:36:12+0530",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.3.1.jar, environment: Java 21.0.10 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserCreationDto toCreationDto(UserEntity user) {
        if ( user == null ) {
            return null;
        }

        UserCreationDto userCreationDto = new UserCreationDto();

        userCreationDto.setUserName( user.getUserName() );
        userCreationDto.setEmailId( user.getEmailId() );
        userCreationDto.setUserPwd( user.getUserPwd() );

        return userCreationDto;
    }

    @Override
    public UserRequestDto toRequestDto(UserEntity user) {
        if ( user == null ) {
            return null;
        }

        UserRequestDto userRequestDto = new UserRequestDto();

        userRequestDto.setUserId( user.getUserId() );
        userRequestDto.setUserName( user.getUserName() );
        userRequestDto.setEmailId( user.getEmailId() );

        return userRequestDto;
    }

    @Override
    public UserEntity toEntity(UserCreationDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUserName( dto.getUserName() );
        userEntity.setEmailId( dto.getEmailId() );
        userEntity.setUserPwd( dto.getUserPwd() );

        return userEntity;
    }
}
