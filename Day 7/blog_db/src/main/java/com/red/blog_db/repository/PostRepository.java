package com.red.blog_db.repository;

import com.red.blog_db.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    PostEntity findByPostTitle(String postTitle);

    PostEntity findByPostId(Long myId);
}
