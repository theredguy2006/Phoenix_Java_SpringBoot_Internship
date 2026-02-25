package com.red.userexceptions.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email_id", unique = true)
    private String emailId;

    @Column(name = "user_pwd")
    private Long userPwd;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
