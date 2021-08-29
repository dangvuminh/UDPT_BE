package com.dang.forumService.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dang.forumService.entity.Category;
import com.dang.forumService.entity.NumberOfComments;
import com.dang.forumService.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dang.forumService.entity.Forum;
import com.dang.forumService.entity.ForumResponse;
import com.dang.forumService.repository.ForumRepository;

@Service
public class ForumService {
	@Autowired
	private ForumRepository forumRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public ForumResponse createQuestion(Forum forum) {
			forum.setNum_of_likes(0);
			forum.setNum_of_comments(0);
			forum.setIs_legal(false);
			Optional<Category> category = categoryRepository.findById(forum.getCategory_id_fk());
			ForumResponse res = restTemplate.getForObject("http://USER-SERVICE/api/user/validate/" + forum.getUser_id_fk(), ForumResponse.class);

			if(res.getStatusCode() ==  204 && category.isPresent()) {
				forumRepository.save(forum);
				return new ForumResponse("forum created","A new forum is created",204);
			}
			return new ForumResponse("failed to create","The user or category is not existing",404);
	}

	public ForumResponse createCategory(Category category) {
		categoryRepository.save(category);
		return new ForumResponse("category created","A new category is created",204);
	}

	public ForumResponse isCategoryExisted(Integer forum_id) {
		Optional<Forum> isForumExisted = forumRepository.findById(forum_id);
		if(isForumExisted.isPresent()) {
			return new ForumResponse("category found","This category can be used",204);
		}
		return new ForumResponse("category not found","This category can't be used",404);
	}

	public List<Forum> getQuestionList(){
		List<Forum> forumList = forumRepository.findAll();
		return forumList.stream().map((q) -> {
			NumberOfComments num = restTemplate.getForObject("http://COMMENT-SERVICE/api/comment/count_comment_by_forum/" + q.getForum_id(), NumberOfComments.class);
			// q.setNum_of_comments(num.getNumberOfComments());
			return new Forum(q.getForum_id(),q.getForum_name(),q.getForum_content(),q.getUser_id_fk(),num.getNumberOfComments(),q.isIs_legal(),q.getNum_of_comments(),q.getTag(),q.getCategory_id_fk());
		}).collect(Collectors.toList());
	}
}
