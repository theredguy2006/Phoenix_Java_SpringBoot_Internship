package com.red.blog_db.repository;

import com.red.blog_db.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    PostEntity findByPostTitle(String postTitle);

    PostEntity findByPostId(Long myId);
}
