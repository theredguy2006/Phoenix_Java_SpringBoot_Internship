package com.red.userjwt.controller;

import com.red.userjwt.dto.UserCreationDto;
import com.red.userjwt.dto.UserRequestDto;
import com.red.userjwt.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired

    private UserService userService;

    @GetMapping
    public Page<UserRequestDto> getAllUser(Pageable page) {
        return userService.getAllUsers(page);
    }

    @PostMapping
    public UserCreationDto createUser(@Valid @RequestBody UserCreationDto userCreationDto) {
        return userService.userCreationDto(userCreationDto);
    }

    //    I made a mistake of having same endpoint or api point with getAllUser and getUserById
    @GetMapping("/byid")
    public UserRequestDto getUserById(@RequestParam Long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/updatebyid")
    public UserRequestDto updateUserById(@RequestParam Long userId, @Valid @RequestBody UserCreationDto userCreationDto) {
        return userService.updateUser(userId, userCreationDto);
    }

    @DeleteMapping("/deletebyid")
    public UserRequestDto deleteUserById(@RequestParam Long userId) {
        return userService.deleteUser(userId);
    }

}
