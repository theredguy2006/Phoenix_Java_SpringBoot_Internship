package com.red.blog_db.service;

import com.red.blog_db.dto.CreatePostDto;
import com.red.blog_db.dto.PostResponseDto;
import com.red.blog_db.entity.PostEntity;
import com.red.blog_db.entity.UserEntity;
import com.red.blog_db.mapper.PostMapper;
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

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostMapper postMapper;

    // 🔹 GET ALL POSTS (PAGINATION)
    public Page<PostResponseDto> findByPagination(Pageable page) {
        return postRepository.findAll(page).map(postMapper::toDTO);
    }

    // 🔹 SORT POSTS BY TITLE
    public Page<PostResponseDto> sortByTitle(Pageable page) {
        return postRepository.findAllByOrderByPostTitle(page).map(postMapper::toDTO);
    }

    // 🔹 GET POST BY ID
    public PostResponseDto getPostById(Long myId) {
        PostEntity post = postRepository.findById(myId).orElseThrow(() -> new RuntimeException("Post not found"));

        return postMapper.toDTO(post);
    }

    // 🔹 CREATE POST
    public PostResponseDto createPost(CreatePostDto request) {

        // Fetch USER ENTITY directly from repository
        UserEntity user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        PostEntity post = new PostEntity();
        post.setPostTitle(request.getPostTitle());
        post.setPostBody(request.getPostBody());
        post.setPostTime(LocalDateTime.now());
        post.setUserEntity(user);   // ✅ Correct type

        PostEntity saved = postRepository.save(post);

        return postMapper.toDTO(saved);
    }

    // 🔹 UPDATE POST
    public PostResponseDto updatePostById(@NotNull @NonNull CreatePostDto request, Long myId) {

        PostEntity post = postRepository.findByPostId(myId);

        if (post == null) {
            throw new RuntimeException("Post not found");
        }
        if (!post.getUserEntity().getUserId().equals(request.getUserId())) {
            throw new RuntimeException("Not allowed");
        }

        post.setPostTitle(request.getPostTitle());
        post.setPostBody(request.getPostBody());
        post.setPostTime(LocalDateTime.now());

        PostEntity updated = postRepository.save(post);

        return postMapper.toDTO(updated);
    }

    // 🔹 DELETE POST
    public void deletePost(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new RuntimeException("Post not found");
        }
        postRepository.deleteById(postId);
    }

    // 🔹 RECENT POSTS
    public List<PostResponseDto> recentPosts() {
        return postRepository.recentPosts().stream().map(postMapper::toDTO).toList();
    }

    // 🔹 SEARCH POSTS BY KEYWORD
    public List<PostResponseDto> findKeyword(String keyword) {
        return postRepository.findPostContainingKeywordInTitle(keyword).stream().map(postMapper::toDTO).toList();
    }

    // 🔹 FIND POSTS BY USER
    public List<PostResponseDto> findUserPosts(Long myId) {
        return postRepository.findPostsByUser(myId).stream().map(postMapper::toDTO).toList();
    }
}