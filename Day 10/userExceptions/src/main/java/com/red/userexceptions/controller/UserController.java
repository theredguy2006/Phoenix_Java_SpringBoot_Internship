package com.red.userexceptions.controller;

import com.red.userexceptions.dto.UserCreationDto;
import com.red.userexceptions.dto.UserRequestDto;
import com.red.userexceptions.entity.UserEntity;
import com.red.userexceptions.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Page<UserRequestDto> getAllUser(Pageable page) {
        return userService.getAllUsers(page);
    }

    @PostMapping
    public UserCreationDto createUser(@Valid @RequestBody UserEntity userEntity) {
        return userService.userCreationDto(userEntity);
    }

    @GetMapping
    public UserRequestDto getAllUserById(@RequestParam Long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping
    public UserRequestDto updateUserById(@RequestParam Long userId, @RequestBody UserEntity userEntity) {
        return userService.updateUser(userId, userEntity);
    }

    @DeleteMapping
    public UserRequestDto deleteUserById(@RequestParam Long userId) {
        return userService.deleteUser(userId);
    }

}
