package com.red.blog_db.controller;

import com.red.blog_db.entity.UserEntity;
import com.red.blog_db.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public void saveUser(@Valid @RequestBody UserEntity userEntity) {
        userService.createUser(userEntity);

    }

    @GetMapping("/id/{myId}")
    public Optional<UserEntity> getUserById(@PathVariable Long myId) {
        return userService.getUserById(myId);
    }

    @DeleteMapping("/id/{myId}")
    public void deleteUserById(@PathVariable Long myId) {
        userService.deleteUser(myId);
    }

    @PutMapping("/id/{myId}")
    public void updateUser(@PathVariable Long myId, @RequestBody UserEntity userEntity) {
        userService.updateUserByUsername(userEntity, myId);
    }
}
