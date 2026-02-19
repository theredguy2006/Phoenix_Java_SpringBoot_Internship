package com.red.blog_db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @Column(name = "postId")
    private Long postId;
    @NotNull
    @Column(name = "postTitle")
    private String postTitle;
    @NotNull
    @Column(name = "postBody")
    private String postBody;
    @Column(name = "postTime")
    private LocalDateTime postTime;


    @OneToMany(mappedBy = "postEntity", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "post-comment")
    private List<CommentEntity> commentEntityList;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"postEntityList", "commentEntityList"})
    private UserEntity userEntity;

}
