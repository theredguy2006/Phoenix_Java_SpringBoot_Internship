package com.red.userexceptions.service;

import com.red.userexceptions.dto.UserCreationDto;
import com.red.userexceptions.dto.UserRequestDto;
import com.red.userexceptions.entity.UserEntity;
import com.red.userexceptions.exception.BadRequestException;
import com.red.userexceptions.exception.DuplicateResourceException;
import com.red.userexceptions.exception.ResourceNotFoundException;
import com.red.userexceptions.mapper.UserMapper;
import com.red.userexceptions.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //    public UserCreationDto userCreationDto(UserEntity userEntity) {
    public UserCreationDto userCreationDto(UserCreationDto userCreationDto) {

        UserEntity userEntity = userMapper.toEntity(userCreationDto);

        if (userRepository.existsByEmailId(userEntity.getEmailId())) {
            throw new DuplicateResourceException("Email already exists");
        }

        userEntity.setCreatedAt(LocalDateTime.now());

        // 🔐 Encode password BEFORE saving
        userEntity.setUserPwd(passwordEncoder.encode(userEntity.getUserPwd()));

        userRepository.save(userEntity);

        return userMapper.toCreationDto(userEntity);
    }

    public Page<UserRequestDto> getAllUsers(Pageable page) {
        Page<UserEntity> users = userRepository.findAll(page);
        return users.map(userMapper::toRequestDto);
    }

    public UserRequestDto deleteUser(Long userId) {

        UserEntity loggedInUser = getLoggedInUser();

        if (!loggedInUser.getUserId().equals(userId)) {
            throw new BadRequestException("You can only delete your own account");
        }

        userRepository.delete(loggedInUser);
        return userMapper.toRequestDto(loggedInUser);
    }

    public UserRequestDto updateUser(Long userId, UserCreationDto userCreationDto) {

        UserEntity loggedInUser = getLoggedInUser();

        if (!loggedInUser.getUserId().equals(userId)) {
            throw new BadRequestException("You can only update your own account");
        }

        if (userCreationDto.getUserName() != null) {
            loggedInUser.setUserName(userCreationDto.getUserName());
        }

        if (userCreationDto.getEmailId() != null && !userCreationDto.getEmailId().equals(loggedInUser.getEmailId()) && userRepository.existsByEmailId(userCreationDto.getEmailId())) {

            throw new DuplicateResourceException("Email already exists");
        }

        if (userCreationDto.getEmailId() != null) {
            loggedInUser.setEmailId(userCreationDto.getEmailId());
        }

        if (userCreationDto.getUserPwd() != null) {
            loggedInUser.setUserPwd(passwordEncoder.encode(userCreationDto.getUserPwd()));
        }

        userRepository.save(loggedInUser);
        return userMapper.toRequestDto(loggedInUser);
    }

    public UserRequestDto getUserById(Long userId) {

        UserEntity user = userRepository.findByUserId(userId);

        if (user == null) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }

        return userMapper.toRequestDto(user);
    }

    private UserEntity getLoggedInUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String loggedInEmail = authentication.getName();

        return userRepository.findByEmailId(loggedInEmail).orElseThrow(() -> new ResourceNotFoundException("Logged in user not found"));
    }
}
