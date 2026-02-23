package com.red.userexceptions.service;

import com.red.userexceptions.dto.UserCreationDto;
import com.red.userexceptions.dto.UserRequestDto;
import com.red.userexceptions.entity.UserEntity;
import com.red.userexceptions.mapper.UserMapper;
import com.red.userexceptions.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public UserCreationDto userCreationDto(UserEntity userEntity) {
        userRepository.save(userEntity);
        userEntity.setCreatedAt(LocalDateTime.now());
        return userMapper.creationDto(userEntity);
    }

    public Page<UserRequestDto> getAllUsers(Pageable page) {
        Page<UserEntity> users = userRepository.findAll(page);
        return users.map(userMapper::requestDto);
    }

    public UserRequestDto deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        UserEntity user = userRepository.findByUserId(userId);
        userRepository.delete(user);
        return userMapper.requestDto(user);
    }

    public UserRequestDto updateUser(Long userId, UserEntity userEntity) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        UserEntity user = userRepository.findByUserId(userId);
        if (userEntity.getUserName() != null) {
            user.setUserName(userEntity.getUserName());
        }

        if (userEntity.getEmailId() != null) {
            user.setEmailId(userEntity.getEmailId());
        }

        if (userEntity.getUserPwd() != null) {
            user.setUserPwd(userEntity.getUserPwd());
        }
        userRepository.save(user);
        return userMapper.requestDto(user);
    }

    public UserRequestDto getUserById(Long userId) {
        return userMapper.requestDto(userRepository.findByUserId(userId));
    }
}
