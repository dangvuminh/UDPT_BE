package com.dang.forumService.service;

import java.util.ArrayList;
import java.util.Arrays;
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

	private List<ForumDisplay> mapForumsWithComments(List<Forum> forumList) {
		return forumList.stream().map((q) -> {
			NumberOfComments num = restTemplate.getForObject("http://COMMENT-SERVICE/api/comment/count_comment_by_forum/" + q.getForum_id(), NumberOfComments.class);
			UserInfo user = restTemplate.getForObject("http://USER-SERVICE/api/user/get_user/" + q.getUser_id_fk(),UserInfo.class);
			Integer numOfLike = getNumOfLike(q.getForum_id());
				return new ForumDisplay(q.getForum_id(),q.getForum_name(),q.getForum_content(),q.getUser_id_fk(),numOfLike,q.isIs_legal(),num.getNumberOfComments(),q.getTagList(),q.getCategory_id_fk(),user.getFirst_name(),user.getLast_name(),user.getProfile_img());
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

	public List<ForumDisplay> getIllegalQuestionList(){
		List<Forum> forumList = forumRepository.findAllByIsLegalEquals(false);
		return mapForumsWithComments(forumList);
	}

	public List<ForumDisplay> getIllegalQuestionListByCategory(Integer categoryId){
		List<Forum> forumList = forumRepository.findAllByCategoryIdAndIsLegal(categoryId,false);
		return mapForumsWithComments(forumList);
	}

	public List<ForumDisplay> getLegalQuestionList(){
		List<Forum> forumList = forumRepository.findAllByIsLegalEquals(true);
		return mapForumsWithComments(forumList);
	}

	public List<ForumDisplay> getLegalQuestionListByCategory(Integer categoryId){
		List<Forum> forumList = forumRepository.findAllByCategoryIdAndIsLegal(categoryId,true);
		return mapForumsWithComments(forumList);
	}

	public Integer getLikeOfForum(Integer forumId) {
		return likeForumRepository.countByForumId(forumId);
	}

	public Integer getLikeOfUser(String userId) {
		List<Forum> forumList = forumRepository.findAllByUserId(userId);
		Integer total = 0;
		for(Forum f:forumList){
			total += f.getNum_of_likes();
		}
		return total;
	}

	public ForumResponse likeForum(LikeForum likeForum) {
		if(doesUserLikeForum(likeForum.getUserId(),likeForum.getForumId()) == false) {
			Optional<Forum> isForumExisted = forumRepository.findById(likeForum.getForumId());
			ForumResponse isUserExisted = restTemplate.getForObject("http://USER-SERVICE/api/user/validate/" + likeForum.getUserId(), ForumResponse.class);
			if(isForumExisted.isPresent() && isUserExisted.getStatusCode() == 204) {
				likeForum.setLiked(true);
				likeForumRepository.save(likeForum);
				forumRepository.updateLikeOfForum(likeForum.getForumId(),getLikeOfForum(likeForum.getForumId()));
				return new ForumResponse("liked","You have liked this forum",204);
			} else {
				return new ForumResponse("failed to like","Forum or user can't be found",404);
			}
		} else {
			Optional<LikeForum> existing = likeForumRepository.findByUserIdAndForumId(likeForum.getUserId(),likeForum.getForumId());
			likeForumRepository.updateLikedForum(likeForum.getUserId(),likeForum.getForumId(),!existing.get().isLiked());
			forumRepository.updateLikeOfForum(likeForum.getForumId(),getLikeOfForum(likeForum.getForumId()));
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

	public ForumResponse legalizeForum(LegalizeForum legalizeForum) {
		ForumResponse isAdmin = restTemplate.getForObject("http://USER-SERVICE/api/user/is_admin/" + legalizeForum.getUserId(),ForumResponse.class);
		if(isAdmin.getStatusCode() == 204) {
			Optional<Forum> forum = forumRepository.findById(legalizeForum.getForumId());
			if(forum.isPresent()) {
				forumRepository.legalizeForum(legalizeForum.getForumId(),!forum.get().isIs_legal());
				if (forum.get().isIs_legal() == false)
					return new ForumResponse("legalized","This forum has been legalized",204);
				return new ForumResponse("illegalized","This forum has been illegalized",204);
			}
			return new ForumResponse("forum not found","This forum has not existing",404);
		}
		return new ForumResponse("fail to legalize","No permission to do that",403);
	}

	public List<Forum> searchByTags(String tagList) {
		List<String> tags = new ArrayList<>(Arrays.asList(tagList.split(",")));
		List<Forum> newList = new ArrayList<>();

		for(Integer i = 0 ; i< tags.size() ;i++) {
			Forum forum = forumRepository.getForumByTags(tags.get(i));
			if(newList.size() == 0) {
				newList.add(forum);
			} else {
				boolean isExisted = false;
				for(Integer j = 0 ; j< newList.size() ;j++) {
					if(forum.getForum_id() == newList.get(j).getForum_id()) {
						isExisted = true;
					}
				}
				if(isExisted == false) {
					newList.add(forum);
				}
			}
		}
		return newList;
	}

	public List<Category> getCategories() {
		return categoryRepository.findAll();
	}

}
