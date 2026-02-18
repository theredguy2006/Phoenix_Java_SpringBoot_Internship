package com.red.blog_db.service;

import com.red.blog_db.entity.PostEntity;
import com.red.blog_db.entity.UserEntity;
import com.red.blog_db.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;


    @Autowired
    private UserService userService;


    public List<PostEntity> getAllPosts() {

        return new ArrayList<>(postRepository.findAll());
    }

    public Optional<PostEntity> getPostById(Long myId) {
        return postRepository.findById(myId);
    }

    public void createPost(PostEntity postEntity, Long myId) {
        try {
            UserEntity user = userService.getUserById(myId);
            postEntity.setUserEntity(user);
            postRepository.save(postEntity);
        } catch (NullPointerException ne) {
            ne.getStackTrace();
            throw new NullPointerException();
        } catch (Exception e) {
            System.out.print("Unexpected Error Besides NullPointer Exception " + e);
            e.getStackTrace();
            System.out.println("Above is the stack Trace ");
        }
    }

    public void updatePostById(PostEntity postEntity, Long myId) {
        try {
            PostEntity post = postRepository.findByPostId(myId);
            post.setPostTitle(postEntity.getPostTitle());
            post.setPostBody(postEntity.getPostBody());
            post.setPostTime(LocalDateTime.now());
            postRepository.save(post);
        } catch (NullPointerException ne) {
            ne.getStackTrace();
            throw new NullPointerException();
        } catch (Exception e) {
            System.out.print("Unexpected Error Besides NullPointer Exception " + e);
            e.getStackTrace();
            System.out.println("Above is the stack Trace ");
        }
    }


    public void deletePost(Long myId) {
        postRepository.deleteById(myId);
    }
}
