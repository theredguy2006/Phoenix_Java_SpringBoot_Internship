package com.red.userexceptions.service;

import com.red.userexceptions.mapper.UserMapper;
import com.red.userexceptions.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;
}
