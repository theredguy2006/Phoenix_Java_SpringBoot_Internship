package com.red.blog_db.service;

import com.red.blog_db.entity.UserEntity;
import com.red.blog_db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    public UserEntity getUserById(Long myId) {
        return userRepository.findByUserId(myId);
    }

    public void createUser(UserEntity userEntity) {
        userEntity.setCreatedAt(LocalDateTime.now());
        userRepository.save(userEntity);
    }

    public void updateUserByUsername(UserEntity userEntity, Long myId) {
        try {
            UserEntity user = userRepository.findByUserId(myId);
            user.setUserName(userEntity.getUserName());
            user.setUserPwd(userEntity.getUserPwd());
            user.setEmailId(userEntity.getEmailId());
            userRepository.save(user);
        } catch (NullPointerException ne) {
            ne.getStackTrace();
            throw new NullPointerException();
        } catch (Exception e) {
            System.out.print("Unexpected Error Besides NullPointer Exception " + e);
            e.getStackTrace();
            System.out.println("Above is the stack Trace ");
        }
    }

    public void deleteUser(Long myId) {
        userRepository.deleteById(myId);
    }


}
