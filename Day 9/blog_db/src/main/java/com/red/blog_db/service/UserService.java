package com.red.blog_db.service;

import com.red.blog_db.entity.UserEntity;
import com.red.blog_db.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<UserEntity> getAllUsers(Pageable page) {

        return userRepository.findAll(page);
    }

    public UserEntity getUserById(Long myId) {
        return userRepository.findByUserId(myId);
    }

    public void createUser(@NonNull UserEntity userEntity) {
        userEntity.setCreatedAt(LocalDateTime.now());
        userRepository.save(userEntity);
    }

    public void updateUserByUsername(@NotNull @NonNull UserEntity userEntity, Long myId) {

        UserEntity user = userRepository.findByUserId(myId);
        user.setUserName(userEntity.getUserName());
        user.setUserPwd(userEntity.getUserPwd());
        user.setEmailId(userEntity.getEmailId());
        userRepository.save(user);
    }

    public void deleteUser(Long myId) {

        if (!userRepository.existsById(myId)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(myId);
    }


}
