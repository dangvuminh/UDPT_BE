package com.dang.commentService.repository;

import com.dang.commentService.entity.NumberOfComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dang.commentService.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer>{
//    String COUNT_COMMENTS = "SELECT count(*) as num_of_comments from comments where forum_id_fk = ?1";
//    @Query(COUNT_COMMENTS)
    Integer countByForumIdEquals(Integer forumId);
}
