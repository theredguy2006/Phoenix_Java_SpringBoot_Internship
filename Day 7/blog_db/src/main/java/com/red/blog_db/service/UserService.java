package com.red.blog_db.service;

import com.red.blog_db.entity.UserEntity;
import com.red.blog_db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    public Optional<UserEntity> getUserById(Long myId) {
        return userRepository.findById(myId);
    }

    public void createUser(UserEntity userEntity) {
        userEntity.setCreatedAt(LocalDateTime.now());
        userRepository.save(userEntity);
    }

    public void updateUserByUsername(UserEntity userEntity, Long myId) {
        UserEntity user = userRepository.findByUserId(myId);
        user.setUserName(userEntity.getUserName());
        user.setUserPwd(userEntity.getUserPwd());
        user.setEmailId(userEntity.getEmailId());
        userRepository.save(user);
    }

    public void deleteUser(Long myId) {
        userRepository.deleteById(myId);
    }


}
