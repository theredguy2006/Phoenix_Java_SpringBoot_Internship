package com.red.blog_db.repository;

import com.red.blog_db.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity , Long> {

   CommentEntity findByCommentId (Long myId);
}
