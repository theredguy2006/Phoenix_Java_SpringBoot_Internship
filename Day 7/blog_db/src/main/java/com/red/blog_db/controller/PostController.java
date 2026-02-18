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

    @DeleteMapping("/id/{myId}")
    public void deletePostById(@PathVariable Long myId) {
        postService.deletePost(myId);
    }

    @PostMapping("/username/{userName}")
    public void createPost(@Valid @RequestBody PostEntity postEntity, @PathVariable String userName) {
        postService.createPost(postEntity, userName);
    }

    @PutMapping("/postid/{postid}")
    public void updatePost(@Valid @RequestBody PostEntity postEntity, @PathVariable Long postid) {
        postService.updateUserByPostname(postEntity, postid);
    }


}
