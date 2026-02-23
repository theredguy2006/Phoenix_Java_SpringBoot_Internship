package com.red.blog_db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

//    @NotNull
//    @NotBlank
//    @Size(min = 20, max = 70)
    @Column(name = "post_title")
    private String postTitle;

//    @NotNull
//    @NotBlank
//    @Size(min = 20, max = 200)
    @Column(name = "post_body")
    private String postBody;

    @Column(name = "post_time")
    private LocalDateTime postTime;

    // 🔹 POST → COMMENTS
    @OneToMany(mappedBy = "postEntity", cascade = CascadeType.ALL)
//    @JsonManagedReference(value = "post-comment")
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    // 🔹 POST → USER (BACK SIDE OF user-post)
    @ManyToOne
    @JoinColumn(name = "user_id")
//    @JsonBackReference(value = "user-post")
    private UserEntity userEntity;
}
