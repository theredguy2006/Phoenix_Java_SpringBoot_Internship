package com.red.blog_db.service;


import com.red.blog_db.entity.CommentEntity;
import com.red.blog_db.entity.PostEntity;
import com.red.blog_db.entity.UserEntity;
import com.red.blog_db.repository.CommentRepository;
import com.red.blog_db.repository.PostRepository;
import com.red.blog_db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<CommentEntity> getAllComments() {

        return new ArrayList<>(commentRepository.findAll());
    }

    public Optional<CommentEntity> getCommentById(Long myId) {

        return commentRepository.findById(myId);
    }

    public void createComment(Long myId, Long postId, CommentEntity commentEntity) {

        UserEntity user = userRepository.findByUserId(myId);
        PostEntity post = postRepository.findByPostId(postId);
        commentEntity.setUserEntity(user);
        commentEntity.setPostEntity(post);
        commentRepository.save(commentEntity);
    }

    public void deleteComment(Long myId) {
        commentRepository.deleteById(myId);
    }


}
