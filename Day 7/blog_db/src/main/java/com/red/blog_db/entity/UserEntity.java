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
    @Column(name = "userId")
    private Long userId;
    @NotNull
    @Column(name = "userName")
    private String userName;
    @NotNull
    @Column(name = "emailId", unique = true)
    private String emailId;
    @NotNull
    @Column(name = "userPwd")
    private Long userPwd;
    @Column(name = "createdAt")
    private LocalDateTime createdAt;


    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PostEntity> postEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "user-comment")
    private List<CommentEntity> commentEntityList;

}

