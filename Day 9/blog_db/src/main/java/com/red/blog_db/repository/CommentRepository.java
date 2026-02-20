package com.red.blog_db.repository;

import com.red.blog_db.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    CommentEntity findByCommentId(Long myId);


//   @Query("select c from CommentEntity where c.userEntity.userId =:theUserId")

    //   the above one on line 15 was the one i wrote myself and it has errors. I am gonna note them down here
//
//   1.) after from CommentEntity. there should be c(or whatever you gave before)
//   2.)The param should match. i did UserId in query but param is UserID. so
//   future self focus on naming. It is half of my problems right now.

//    the bottom one is the correct modified version of this query.
@Query("select c from CommentEntity c where c.userEntity.userId = :theUserId")
List<CommentEntity> findCommentByUser(@Param("theUserId") Long theUserId);

}
