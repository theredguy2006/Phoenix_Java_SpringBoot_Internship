package com.red.blog_db.service;

import com.red.blog_db.entity.PostEntity;
import com.red.blog_db.entity.UserEntity;
import com.red.blog_db.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;


    public List<PostEntity> getAllPosts() {

        return new ArrayList<>(postRepository.findAll());
    }

    public Optional<PostEntity> getPostById(Long myId) {
        return postRepository.findById(myId);
    }

    public void createPost(PostEntity postEntity, String userName) {
        UserEntity user = userService.getByUsername(userName);
        postEntity.setUserEntity(user);
        postRepository.save(postEntity);
    }

    public void updateUserByPostname(PostEntity postEntity, Long  myId) {
        PostEntity post = postRepository.findByPostId(myId);
        post.setPostTitle(postEntity.getPostTitle());
        post.setPostBody(postEntity.getPostBody());
        post.setPostTime(LocalDateTime.now());
        postRepository.save(post);
    }


    public void deletePost(Long myId) {
        postRepository.deleteById(myId);
    }
}
