package com.red.blog_db.service;

import com.red.blog_db.dto.CreateUserRequestDTO;
import com.red.blog_db.dto.UpdateUserRequestDTO;
import com.red.blog_db.dto.UserResponseDTO;
import com.red.blog_db.entity.UserEntity;
import com.red.blog_db.mapper.UserMapper;
import com.red.blog_db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public Page<UserResponseDTO> getAllUsers(Pageable page) {

        Page<UserEntity> users = userRepository.findAll(page);

        return users.map(userMapper::toDTO);
    }

    public UserResponseDTO getUserById(Long myId) {

        UserEntity user = userRepository.findByUserId(myId);

        if (user == null) {
            throw new RuntimeException("User Not found ");
        }
        return userMapper.toDTO(user);
    }

    public UserResponseDTO createUser(CreateUserRequestDTO request) {

        UserEntity user = userMapper.toEntity(request);
        user.setCreatedAt(LocalDateTime.now());

        UserEntity saved = userRepository.save(user);

        return userMapper.toDTO(saved);
    }

    public UserResponseDTO updateUser(Long myId, UpdateUserRequestDTO request) {
        UserEntity user = userRepository.findByUserId(myId);

        if (user == null) {
            throw new RuntimeException("User Not found");
        }

        userMapper.updateEntity(user, request);

        UserEntity saved = userRepository.save(user);

        return userMapper.toDTO(saved);
    }

    public void deleteUser(Long myId) {

        if (!userRepository.existsById(myId)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(myId);
    }


}
