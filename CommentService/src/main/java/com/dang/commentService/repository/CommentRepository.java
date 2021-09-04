package com.dang.commentService.repository;

import com.dang.commentService.entity.NumberOfComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dang.commentService.entity.Comment;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer>{
//    String COUNT_COMMENTS = "SELECT count(*) as num_of_comments from comments where forum_id_fk = ?1";
//    @Query(COUNT_COMMENTS)
    Integer countByForumIdEquals(Integer forumId);

    Optional<Comment> findByCommentIdAndUserIdAndForumId(Integer commentId,String userId, Integer forumId);
    @Modifying
    @Transactional
    void deleteByForumIdAndUserIdAndCommentId(Integer forumId, String userId,Integer commentId);

    @Modifying
    @Transactional
    @Query("update Comment c set c.commentContent = ?4 where c.commentId = ?1 and c.userId = ?2 and c.forumId = ?3")
    void updateCommentByIdAndUserIdAndForumId(Integer commentId,String userId,Integer forumId,String commentContent);

    @Modifying
    @Transactional
    @Query("update Comment c set c.num_of_likes = ?3 where c.commentId = ?1 and c.forumId = ?2")
    void updateLikeOfComment(Integer commentId,Integer forumId,Integer likes);

    Integer countByUserIdEquals(String userId);

    List<Comment> findAllByUserId(String userId);

}
