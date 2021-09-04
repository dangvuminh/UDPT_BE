package com.dang.forumService.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dang.forumService.entity.*;
import com.dang.forumService.repository.CategoryRepository;
import com.dang.forumService.repository.LikeForumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dang.forumService.repository.ForumRepository;


@Service
public class ForumService {
	@Autowired
	private ForumRepository forumRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private LikeForumRepository likeForumRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	private List<Forum> mapForumsWithComments(List<Forum> forumList) {
		return forumList.stream().map((q) -> {
			NumberOfComments num = restTemplate.getForObject("http://COMMENT-SERVICE/api/comment/count_comment_by_forum/" + q.getForum_id(), NumberOfComments.class);
			Integer numOfLike = getNumOfLike(q.getForum_id());
				return new Forum(q.getForum_id(),q.getForum_name(),q.getForum_content(),q.getUser_id_fk(),numOfLike,q.isIs_legal(),num.getNumberOfComments(),q.getTagList(),q.getCategory_id_fk());
		}).collect(Collectors.toList());
	}
	boolean doesUserLikeForum(String userId,Integer forumId) {
		Optional<LikeForum> doesUserLikeForum = likeForumRepository.findByUserIdAndForumId(userId,forumId);
		if(doesUserLikeForum.isPresent()) {
			return true;
		}
		return false;
	}
	public ForumResponse createQuestion(Forum forum) {
			forum.setNum_of_likes(0);
			forum.setNum_of_comments(0);
			forum.setIs_legal(false);
			Optional<Category> category = categoryRepository.findById(forum.getCategory_id_fk());
			ForumResponse res = restTemplate.getForObject("http://USER-SERVICE/api/user/validate/" + forum.getUser_id_fk(), ForumResponse.class);
			if(res.getStatusCode() == 204 && category.isPresent()) {
				if(forum.getTagList() != null) {
					HttpEntity<TagListString> request = new HttpEntity<>(new TagListString(forum.getTagList()));
					ForumResponse areTagsValid = restTemplate.postForObject("http://TAG-SERVICE/api/tag/is_tag_valid", request, ForumResponse.class);
					if(areTagsValid.getStatusCode() == 204) {
						forumRepository.save(forum);
						return new ForumResponse("forum created","A new forum is created",204);
					}
					else {
					return new ForumResponse("forum created",areTagsValid.getMessage(),areTagsValid.getStatusCode());
					}
				}
				return new ForumResponse("forum created","A new forum is created",204);
			}
			return new ForumResponse("failed to create","The user or category is not existing",404);
	}

	public ForumResponse createCategory(Category category) {
		categoryRepository.save(category);
		return new ForumResponse("category created","A new category is created",204);
	}

	public ForumResponse isForumExisted(Integer forum_id) {
		Optional<Forum> isForumExisted = forumRepository.findById(forum_id);
		if(isForumExisted.isPresent()) {
			return new ForumResponse("forum found","This forum can be used",204);
		}
		return new ForumResponse("forum not found","This forum can't be used",404);
	}

	public List<Forum> getIllegalQuestionList(){
		List<Forum> forumList = forumRepository.findAllByIsLegalEquals(false);
		return mapForumsWithComments(forumList);
	}

	public List<Forum> getIllegalQuestionListByCategory(Integer categoryId){
		List<Forum> forumList = forumRepository.findAllByCategoryIdAndIsLegal(categoryId,false);
		return mapForumsWithComments(forumList);
	}

	public List<Forum> getLegalQuestionList(){
		List<Forum> forumList = forumRepository.findAllByIsLegalEquals(true);
		return mapForumsWithComments(forumList);
	}

	public List<Forum> getLegalQuestionListByCategory(Integer categoryId){
		List<Forum> forumList = forumRepository.findAllByCategoryIdAndIsLegal(categoryId,true);
		return mapForumsWithComments(forumList);
	}

	public ForumResponse likeForum(LikeForum likeForum) {
		if(doesUserLikeForum(likeForum.getUserId(),likeForum.getForumId()) == false) {
			Optional<Forum> isForumExisted = forumRepository.findById(likeForum.getForumId());
			ForumResponse isUserExisted = restTemplate.getForObject("http://USER-SERVICE/api/user/validate/" + likeForum.getUserId(), ForumResponse.class);
			if(isForumExisted.isPresent() && isUserExisted.getStatusCode() == 204) {
				likeForum.setLiked(true);
				likeForumRepository.save(likeForum);
				return new ForumResponse("liked","You have liked this forum",204);
			} else {
				return new ForumResponse("failed to like","Forum or user can't be found",404);
			}
		} else {
			Optional<LikeForum> existing = likeForumRepository.findByUserIdAndForumId(likeForum.getUserId(),likeForum.getForumId());
			likeForumRepository.updateLikedForum(likeForum.getUserId(),likeForum.getForumId(),!existing.get().isLiked());
			if(existing.get().isLiked() == false)
			return new ForumResponse("liked","You have liked this forum",204);
			return new ForumResponse("unliked","You have unliked this forum",204);
		}
	}

	public Integer getNumOfLike(Integer forumId) {
		Integer numOfLike = likeForumRepository.countByForumIdAndIsLikedEquals(forumId,true);
		return numOfLike;
	}

	public ForumResponse deleteForum(DeleteForum deleteForum) {
		Optional<Forum> isForumExisted = forumRepository.findById(deleteForum.getForumId());
		ForumResponse isUserExisted = restTemplate.getForObject("http://USER-SERVICE/api/user/validate/" + deleteForum.getUserId(), ForumResponse.class);
		if(isForumExisted.isPresent() &&  isUserExisted.getStatusCode() == 204) {
			forumRepository.deleteByForumIdAndUserId(deleteForum.getForumId(),deleteForum.getUserId());
			return new ForumResponse("deleted","This forum has been deleted",204);
		}
		return new ForumResponse("fail to delete","This forum can't be deleted",403);
	}

}
