package com.red.blog_db.controller;

import com.red.blog_db.entity.PostEntity;
import com.red.blog_db.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public Page<PostEntity> getAllPosts(@PageableDefault(size = 5, page = 0) Pageable page) {
        return postService.findByPagination(page);
    }

    @GetMapping("/id/{myId}")
    public Optional<PostEntity> getPostById(@PathVariable Long myId) {
        return postService.getPostById(myId);
    }

    @DeleteMapping("/postid/{postId}")
    public void deletePostById(@PathVariable Long postId) {
        postService.deletePost(postId);
    }

    @PostMapping("/userid/{myId}")
    public void createPost(@Valid @RequestBody PostEntity postEntity, @PathVariable Long myId) {
        postService.createPost(postEntity, myId);
    }

    @PutMapping("/postid/{postId}")
    public void updatePost(@Valid @RequestBody PostEntity postEntity, @PathVariable Long postId) {
        postService.updatePostById(postEntity, postId);
    }

    @GetMapping("/recent")
    public List<PostEntity> recentPosts() {
        return postService.recentPosts();
    }

    @GetMapping("/search/{keyword}")
    public List<PostEntity> findKeyword(@PathVariable String keyword) {
        return postService.findKeyword(keyword);
    }

    @GetMapping("/byuser/{myId}")
    public List<PostEntity> findUserPosts(@PathVariable Long myId) {
        return postService.findUserPosts(myId);
    }


}
