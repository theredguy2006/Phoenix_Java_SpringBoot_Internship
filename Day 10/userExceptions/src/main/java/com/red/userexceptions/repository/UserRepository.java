package com.red.userexceptions.repository;

import com.red.userexceptions.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserId(Long userId);

    boolean existsByEmailId(String emailId);

    Optional<UserEntity> findByEmailId(String emailId);
}
