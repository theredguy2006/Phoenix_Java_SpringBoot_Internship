package com.red.blog_db.controller;

import com.red.blog_db.entity.CommentEntity;
import com.red.blog_db.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<CommentEntity> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/id/{myId}")
    public Optional<CommentEntity> getCommentById(@PathVariable Long myId) {
        return commentService.getCommentById(myId);
    }

    @DeleteMapping("/id/{myId}")
    public void deleteCommentById(@PathVariable Long myId) {
        commentService.deleteComment(myId);
    }

    @PostMapping("/postid/{postId}/userid/{myId}")
    public void createComment(@Valid @RequestBody CommentEntity commentEntity, @PathVariable Long myId, @PathVariable Long postId) {
        commentService.createComment(postId, myId, commentEntity);
    }

}
