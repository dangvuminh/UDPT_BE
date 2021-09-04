package com.dang.forumService.repository;

import com.dang.forumService.entity.LikeForum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface LikeForumRepository extends JpaRepository<LikeForum,Integer> {
    Optional<LikeForum> findByUserIdAndForumId(String userId, Integer forumId);

    @Transactional
    @Modifying
    @Query("update LikeForum l set l.isLiked = ?3 where l.forumId = ?2 and l.userId = ?1")
    void updateLikedForum(String userId,Integer forumId,boolean isLiked);

    Integer countByForumIdAndIsLikedEquals(Integer forumId,boolean isLiked);

    Integer countByForumId(Integer forumId);
}
