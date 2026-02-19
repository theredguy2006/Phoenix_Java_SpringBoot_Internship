package com.red.blog_db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @NotNull
    @Column(name = "comment_body")
    private String commentBody;

    @Column(name = "comment_time")
    private LocalDateTime commentTime;


    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnoreProperties({"commentEntityList"})
    private PostEntity postEntity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"postEntityList", "commentEntityList"})
    private UserEntity userEntity;

}
