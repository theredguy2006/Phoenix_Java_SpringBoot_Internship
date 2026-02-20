package com.red.blog_db.controller;

import com.red.blog_db.entity.PostEntity;
import com.red.blog_db.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public List<PostEntity> getAllPosts() {
        return postService.getAllPosts();
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
