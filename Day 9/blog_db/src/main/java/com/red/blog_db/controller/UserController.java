package com.red.blog_db.controller;

import com.red.blog_db.entity.UserEntity;
import com.red.blog_db.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Page<UserEntity> getAllUsers(@PageableDefault(sort = "userName", page = 0, size = 5) Pageable page) {
        return userService.getAllUsers(page);
    }

    @PostMapping
    public void saveUser(@Valid @RequestBody UserEntity userEntity) {
        userService.createUser(userEntity);

    }

    @GetMapping("/id/{myId}")
    public UserEntity getUserById(@PathVariable Long myId) {
        return userService.getUserById(myId);
    }

    @DeleteMapping("/id/{myId}")
    public void deleteUserById(@PathVariable Long myId) {
        userService.deleteUser(myId);
    }

    @PutMapping("/id/{myId}")
    public void updateUser(@PathVariable Long myId, @Valid @RequestBody UserEntity userEntity) {
        userService.updateUserByUsername(userEntity, myId);
    }
}
