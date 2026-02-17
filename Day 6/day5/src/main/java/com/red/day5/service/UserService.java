package com.red.day5.service;

import com.red.day5.entity.UserEntity;
import com.red.day5.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public void saveUser(UserEntity userEntityEntry) {
        userRepo.save(userEntityEntry);
    }

    public List<UserEntity> getAllUser() {
        return userRepo.findAll();

    }


    public Optional<UserEntity> getUserById(Long id) {
        return userRepo.findById(id);
    }

    public void deleteUserByID(Long id) {

        userRepo.deleteById(id);
    }

    public UserEntity findByUserName(String UserName)
    {
        return userRepo.findByUserName(UserName);
    }
}
