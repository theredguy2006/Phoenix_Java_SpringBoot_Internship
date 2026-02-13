package com.red.day5.controller;

import com.red.day5.entity.UserEntity;
import com.red.day5.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserEntity> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/id/{myId}")
    public Optional<UserEntity> getUserById(@PathVariable long myId) {
        return userService.getUserById(myId);
    }

    @PostMapping
    public ResponseEntity<UserEntity> saveUser(@Valid @RequestBody UserEntity userEntityEntry) {
        try {
            userEntityEntry.setDate(LocalDateTime.now());
            userService.saveUser(userEntityEntry);
            return new ResponseEntity<>(userEntityEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.print(e + "Error and Exception ");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{userName}")
    public ResponseEntity<UserEntity> updateUser(@Valid @RequestBody UserEntity userEntity, @PathVariable String userName ) {
        UserEntity userEntityInDb = userService.findByUserName(userName);
        if (userEntityInDb != null) {
            userEntityInDb.setUserName(userEntity.getUserName());
            userEntityInDb.setEmailID(userEntity.getEmailID());
            userEntityInDb.setUserPWD(userEntity.getUserPWD());
            userService.saveUser(userEntityInDb);
            return new ResponseEntity<>(userEntityInDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }



}
