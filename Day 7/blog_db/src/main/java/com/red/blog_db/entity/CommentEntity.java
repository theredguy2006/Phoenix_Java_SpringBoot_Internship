package com.red.blog_db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId")
    private Long commentId;

    @NotNull
    @Column(name = "commentBody")
    private String commentBody;

    @Column(name = "commentTime")
    private LocalDateTime commentTime;


    @ManyToOne
    @JoinColumn(name = "postId", nullable = false)
    @JsonBackReference
    private PostEntity postEntity;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonBackReference
    private UserEntity userEntity;
}
