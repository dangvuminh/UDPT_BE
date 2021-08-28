package com.dang.forumService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dang.forumService.entity.Forum;
import com.dang.forumService.entity.ForumResponse;
import com.dang.forumService.service.ForumService;

@RestController
@RequestMapping("/api/forum")
public class ForumCommandController {
	
	@Autowired
	ForumService forumService;
	
	@PostMapping("/create_question")
	public ForumResponse createQuestion(@RequestBody Forum forum) {
		return forumService.createQuestion(forum);
	}	

}
