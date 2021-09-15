package com.dang.commentService.repository;

import com.dang.commentService.entity.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface LikeCommentRepository extends JpaRepository<LikeComment,Integer> {
    Optional<LikeComment> findByCommentIdAndUserIdAndForumId(Integer commentId,String userId,Integer forumId);

    @Modifying
    @Transactional
    @Query("update LikeComment l set l.isLiked = ?4 where l.commentId = ?1 and l.userId = ?2 and l.forumId = ?3")
    void updateLikeComment(Integer commentId, String userId, Integer forumId, boolean isLiked);

    Integer countByCommentIdAndForumIdAndIsLikedEquals(Integer commentId,Integer forumId,boolean isLiked);

    List<LikeComment> findAllByForumIdAndUserIdAndIsLiked(Integer forumId,String userId, boolean isLiked);
}
