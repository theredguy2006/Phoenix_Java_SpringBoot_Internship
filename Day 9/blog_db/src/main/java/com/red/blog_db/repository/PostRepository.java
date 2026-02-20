package com.red.blog_db.repository;

import com.red.blog_db.entity.PostEntity;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    PostEntity findByPostTitle(String postTitle);

    PostEntity findByPostId(Long myId);

    //    This is let's a way to use the sql query the native sql query otherwise we would have to use.
//    @Query(value = "select p from PostEntity p where p.userEntity.userId?=1")
//    Here "p" is a variable and "PostEntity" is the object type / name of the entity
//    p.userEntity , here userEntity is the variable by which
//    postentity is connected to userEntity and p.user.userId is object navigation.
    @Query(value = "select * from posts where user_id=?1 ", nativeQuery = true)
    List<PostEntity> findPostsByUser(Long myId);


    //    @Query("select p from PostEntity p where ?1 in p.postTitle")
//
//    Again the above one i wrote is error filled so here's the correct version. '
//    1.) Use params cause they are da best. and reliable
//     2.) Also the ?1 in is also wrong and the actual query is more complex than that.


    //    The bottom will ensure that keyword can be anywhere in the title instead of keyword=title,
    @Query("select p from PostEntity p where lower(p.postTitle) like lower(concat('%', :keyword, '%'))")
    List<PostEntity> findPostContainingKeywordInTitle(@Param("keyword") String keyword);

//    @Query("select p from PostEntity p sortBy p.postTime")

    //    The above again is error filled.


    //    The Bottom is now the correct version.
    @Query("select p from PostEntity p order by p.postTime desc")
    List<PostEntity> recentPosts();



    Page<PostEntity> findAll(@NonNull Pageable page);


}
