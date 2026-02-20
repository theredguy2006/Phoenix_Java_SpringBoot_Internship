package com.red.blog_db.service;


import com.red.blog_db.entity.CommentEntity;
import com.red.blog_db.entity.PostEntity;
import com.red.blog_db.entity.UserEntity;
import com.red.blog_db.repository.CommentRepository;
import com.red.blog_db.repository.PostRepository;
import com.red.blog_db.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Page<CommentEntity> getAllComments(Pageable page) {

        return commentRepository.findAll(page);
    }

    public Optional<CommentEntity> getCommentById(Long myId) {

        return commentRepository.findById(myId);
    }

    public void createComment(Long myId, Long postId, @NotNull @NonNull CommentEntity commentEntity) throws RuntimeException {


        UserEntity user = userRepository.findById(myId).orElseThrow(() -> new RuntimeException("User not found"));
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        commentEntity.setUserEntity(user);
        commentEntity.setPostEntity(post);
        commentEntity.setCommentTime(LocalDateTime.now());
        commentRepository.save(commentEntity);

    }

    public void deleteComment(Long myId) {
        if (!commentRepository.existsById(myId)) {
            throw new RuntimeException("Comment not found");
        }
        commentRepository.deleteById(myId);
    }

    public List<CommentEntity> userComment(Long userId) {
        return commentRepository.findCommentByUser(userId);
    }

}
