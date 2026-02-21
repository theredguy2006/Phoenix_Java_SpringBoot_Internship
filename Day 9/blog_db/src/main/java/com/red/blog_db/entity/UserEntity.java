package com.red.blog_db.entity;

import jakarta.persistence.*;
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


    @Column(name = "user_name")
    private String userName;

    @Column(name = "email_id", unique = true)
    private String emailId;

    //    @NotNull
//    @NotBlank
//    Same error as below one
//    @Size(min = 4, max = 15)
//    Size is for collections , strings and arrays. Not for numeric values.
//    I made the mistake and hope so do not repeat it again.
//    @Min(1000)
//    @Max(9999)
    @Column(name = "user_pwd")
    private Long userPwd;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 🔹 USER → POSTS
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
//    @JsonManagedReference(value = "user-post")
    private List<PostEntity> postEntityList = new ArrayList<>();

    // 🔹 USER → COMMENTS
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
//    @JsonManagedReference(value = "user-comment")
    private List<CommentEntity> commentEntityList = new ArrayList<>();
}
