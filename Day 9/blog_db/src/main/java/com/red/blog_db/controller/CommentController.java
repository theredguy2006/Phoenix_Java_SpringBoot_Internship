package com.red.blog_db.controller;

import com.red.blog_db.entity.CommentEntity;
import com.red.blog_db.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public Page<CommentEntity> getAllComments(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "5") int size, @RequestParam(required = false, defaultValue = "ASC") String direction, @RequestParam(required = false, defaultValue = "commentId") String sortBy) {
        Sort sort = null;
        if (direction.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        return commentService.getAllComments(pageable);
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
        commentService.createComment(myId, postId, commentEntity);
    }

    @GetMapping("/usercomment/{userId}")
    public List<CommentEntity> userComment(@PathVariable Long userId) {
        return commentService.userComment(userId);
    }

}
