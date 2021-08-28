package com.dang.forumService.service;

import java.util.Objects;

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
	private RestTemplate restTemplate;
	
	public ForumResponse createQuestion(Forum forum) {
			forum.setNum_of_likes(0);
			forum.setNum_of_comments(0);
			forum.setIs_legal(false);
			ForumResponse res = restTemplate.getForObject("http://USER-SERVICE/api/user/validate/" + forum.getUser_id_fk(), ForumResponse.class);
	
			if(res.getStatusCode() ==  "204") {
				forumRepository.save(forum);
				return new ForumResponse("forum created","A new forum is created","204");
			}
			return new ForumResponse("failed to create","The user is not existing","404");
	}
}
