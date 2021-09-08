package com.dang.forumService.repository;

import com.dang.forumService.entity.LikeForum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dang.forumService.entity.Forum;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ForumRepository extends JpaRepository<Forum,Integer>{
	List<Forum> findByCategoryIdEquals(Integer categoryId);
	List<Forum>  findAllByIsLegalEquals(boolean isLegal);
	List<Forum> findAllByCategoryIdAndIsLegal(Integer categoryId,boolean isLegal);

	@Modifying
	@Transactional
	void deleteByForumIdAndUserId(Integer forumId, String userId);

	@Modifying
	@Transactional
	@Query("update Forum f set f.num_of_likes = ?2 where f.forumId = ?1")
	void updateLikeOfForum(Integer forumId,Integer likes);

	List<Forum> findAllByUserId(String userId);

	@Modifying
	@Transactional
	@Query("update Forum f set f.isLegal = ?2 where f.forumId = ?1")
	void legalizeForum(Integer forumId,boolean isLegal);

	@Query("from Forum f where f.tagList like %?1%")
	Forum getForumByTags(String tag);
}
