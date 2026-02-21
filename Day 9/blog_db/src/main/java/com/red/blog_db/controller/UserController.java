package com.red.blog_db.controller;

import com.red.blog_db.dto.CreateUserRequestDTO;
import com.red.blog_db.dto.UpdateUserRequestDTO;
import com.red.blog_db.dto.UserResponseDTO;
import com.red.blog_db.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Page<UserResponseDTO> getAllUsers(@PageableDefault(sort = "userName", size = 5) Pageable page) {
        return userService.getAllUsers(page);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody CreateUserRequestDTO request) {
        UserResponseDTO response = userService.createUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/id/{myId}")
    public UserResponseDTO getUserById(@PathVariable Long myId) {
        return userService.getUserById(myId);
    }

    @DeleteMapping("/id/{myId}")
    public void deleteUserById(@PathVariable Long myId) {
        userService.deleteUser(myId);
    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long myId, @Valid @RequestBody UpdateUserRequestDTO request) {

        UserResponseDTO response = userService.updateUser(myId, request);
        return ResponseEntity.ok(response);
    }
}
