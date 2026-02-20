package com.red.blog_db.service;

import com.red.blog_db.entity.PostEntity;
import com.red.blog_db.entity.UserEntity;
import com.red.blog_db.repository.PostRepository;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public void createPost(@NotNull @NonNull PostEntity postEntity, Long myId) {
        UserEntity user = userService.getUserById(myId);
        postEntity.setPostTime(LocalDateTime.now());
        postEntity.setUserEntity(user);
        postRepository.save(postEntity);
    }

    public void updatePostById(@NotNull @NonNull PostEntity postEntity, Long myId) {

        PostEntity post = postRepository.findByPostId(myId);
        post.setPostTitle(postEntity.getPostTitle());
        post.setPostBody(postEntity.getPostBody());
        post.setPostTime(LocalDateTime.now());
        postRepository.save(post);
    }


    public void deletePost(Long postId) {

        if (!postRepository.existsById(postId)) {
            throw new RuntimeException("Post not found");
        }
        postRepository.deleteById(postId);
    }

    public List<PostEntity> recentPosts() {
        return postRepository.recentPosts();
    }

    public List<PostEntity> findKeyword(String keyword) {
        return postRepository.findPostContainingKeywordInTitle(keyword);
    }

    public List<PostEntity> findUserPosts(Long myId) {
        return postRepository.findPostsByUser(myId);
    }


    public Page<PostEntity> findByPagination(Pageable page) {
        return postRepository.findAll(page);
    }

    public Page<PostEntity> sortByTitle(Pageable page) {
        return postRepository.findAllByOrderByPostTitle(page);
    }
}
