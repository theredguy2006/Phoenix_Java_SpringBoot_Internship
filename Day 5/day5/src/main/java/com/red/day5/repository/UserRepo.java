package com.red.day5.repository;

import com.red.day5.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

    //    User findByUserName(String U_Name);
//    this gives error cause idk how and why(At least right now) the findBy+"UserName"
//    the userName was the one creating this errors right now
    UserEntity findByUserName(String UserName);
}
