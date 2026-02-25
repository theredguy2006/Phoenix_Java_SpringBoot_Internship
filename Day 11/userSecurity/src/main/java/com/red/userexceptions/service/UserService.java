package com.red.userexceptions.service;

import com.red.userexceptions.dto.UserCreationDto;
import com.red.userexceptions.dto.UserRequestDto;
import com.red.userexceptions.entity.UserEntity;
import com.red.userexceptions.exception.DuplicateResourceException;
import com.red.userexceptions.exception.ResourceNotFoundException;
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

    //    public UserCreationDto userCreationDto(UserEntity userEntity) {
    public UserCreationDto userCreationDto(UserCreationDto userCreationDto) {

        UserEntity userEntity = userMapper.toEntity(userCreationDto);
        if (userRepository.existsByEmailId(userEntity.getEmailId())) {
            throw new DuplicateResourceException("Email already exists");
        }

        userEntity.setCreatedAt(LocalDateTime.now());
        userRepository.save(userEntity);

        return userMapper.toCreationDto(userEntity);
    }

    public Page<UserRequestDto> getAllUsers(Pageable page) {
        Page<UserEntity> users = userRepository.findAll(page);
        return users.map(userMapper::toRequestDto);
    }

    public UserRequestDto deleteUser(Long userId) {
        UserEntity user = userRepository.findByUserId(userId);

        if (user == null) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }

        userRepository.delete(user);
        return userMapper.toRequestDto(user);
    }

    public UserRequestDto updateUser(Long userId, UserCreationDto userCreationDto) {

        UserEntity userEntity = userMapper.toEntity(userCreationDto);
        UserEntity user = userRepository.findByUserId(userId);

        if (user == null) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }

        if (userEntity.getUserName() != null) {
            user.setUserName(userEntity.getUserName());
        }

        // Check duplicate ONLY if email is being changed
        if (userEntity.getEmailId() != null && !userEntity.getEmailId().equals(user.getEmailId()) && userRepository.existsByEmailId(userEntity.getEmailId())) {

            throw new DuplicateResourceException("Email already exists");
        }

        if (userEntity.getEmailId() != null) {
            user.setEmailId(userEntity.getEmailId());
        }

        if (userEntity.getUserPwd() != null) {
            user.setUserPwd(userEntity.getUserPwd());
        }

        userRepository.save(user);
        return userMapper.toRequestDto(user);
    }

    public UserRequestDto getUserById(Long userId) {

        UserEntity user = userRepository.findByUserId(userId);

        if (user == null) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }

        return userMapper.toRequestDto(user);
    }
}
