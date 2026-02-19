package com.red.blog_db.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Column(name = "user_name")
    private String userName;

    @NotNull
    @Column(name = "email_id", unique = true)
    private String emailId;

    @NotNull
    @Column(name = "user_pwd")
    private Long userPwd;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 🔹 USER → POSTS
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "user-post")
    private List<PostEntity> postEntityList = new ArrayList<>();

    // 🔹 USER → COMMENTS
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "user-comment")
    private List<CommentEntity> commentEntityList = new ArrayList<>();
}
