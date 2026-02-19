package com.red.blog_db.repository;

import com.red.blog_db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByUserName(String userName);

    UserEntity findByUserId(Long myId);
}
