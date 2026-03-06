package com.red.userjwt.security;

import com.red.userjwt.entity.UserEntity;
import com.red.userjwt.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserEntity user = userRepository.findByEmailId(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        // Hardcoded ADMIN role for learning purposes
        String role = "USER";

        if (user.getEmailId().equals("theredguy2006@gmail.com")) {
            role = "ADMIN";
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmailId())
                .password(user.getUserPwd())
                .roles(role)
                .build();
    }
}