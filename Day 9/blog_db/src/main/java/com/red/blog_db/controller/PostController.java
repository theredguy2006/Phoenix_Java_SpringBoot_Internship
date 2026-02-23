package com.red.blog_db.controller;

import com.red.blog_db.dto.CreatePostDto;
import com.red.blog_db.dto.PostResponseDto;
import com.red.blog_db.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // 🔹 GET ALL POSTS (PAGINATION)
    @GetMapping
    public Page<PostResponseDto> getAllPosts(
            @PageableDefault(size = 5, page = 0) Pageable page) {
        return postService.findByPagination(page);
    }

    // 🔹 SORT POSTS BY TITLE
    @GetMapping("/bytitle")
    public Page<PostResponseDto> getByTitles(
            @PageableDefault(direction = Sort.Direction.ASC) Pageable pageable) {
        return postService.sortByTitle(pageable);
    }

    // 🔹 GET POST BY ID
    @GetMapping("/id/{myId}")
    public PostResponseDto getPostById(@PathVariable Long myId) {
        return postService.getPostById(myId);
    }

    // 🔹 CREATE POST
    @PostMapping
    public PostResponseDto createPost(
            @Valid @RequestBody CreatePostDto request) {
        return postService.createPost(request);
    }

    // 🔹 UPDATE POST
    @PutMapping("/postid/{postId}")
    public PostResponseDto updatePost(
            @Valid @RequestBody CreatePostDto request,
            @PathVariable Long postId) {
        return postService.updatePostById(request, postId);
    }

    // 🔹 DELETE POST
    @DeleteMapping("/postid/{postId}")
    public void deletePostById(@PathVariable Long postId) {
        postService.deletePost(postId);
    }

    // 🔹 RECENT POSTS
    @GetMapping("/recent")
    public List<PostResponseDto> recentPosts() {
        return postService.recentPosts();
    }

    // 🔹 SEARCH POSTS
    @GetMapping("/search/{keyword}")
    public List<PostResponseDto> findKeyword(@PathVariable String keyword) {
        return postService.findKeyword(keyword);
    }

    // 🔹 POSTS BY USER
    @GetMapping("/byuser/{myId}")
    public List<PostResponseDto> findUserPosts(@PathVariable Long myId) {
        return postService.findUserPosts(myId);
    }
}