package com.dang.forumService.repository;

import com.dang.forumService.entity.LikeForum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dang.forumService.entity.Forum;

import java.util.List;

@Repository
public interface ForumRepository extends JpaRepository<Forum,Integer>{
	List<Forum> findByCategoryIdEquals(Integer categoryId);
	List<Forum>  findAllByIsLegalEquals(boolean isLegal);
	List<Forum> findAllByCategoryIdAndIsLegal(Integer categoryId,boolean isLegal);
}
