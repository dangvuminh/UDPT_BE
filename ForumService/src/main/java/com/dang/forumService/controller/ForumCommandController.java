package com.dang.forumService.controller;

import com.dang.forumService.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping("/create_category")
	public ForumResponse createCategory(@RequestBody Category category) {
		return forumService.createCategory(category);
	}

	@PostMapping("/like_forum")
	public ForumResponse likeForum(@RequestBody LikeForum likeForum) {
		return forumService.likeForum(likeForum);
	}

	@PutMapping("/legalize_forum")
	public ForumResponse legalizeForum(@RequestBody LegalizeForum legalizeForum) {
		return forumService.legalizeForum(legalizeForum);
	}
	@DeleteMapping("/delete_forum")
	public ForumResponse deleteForum(@RequestBody DeleteForum deleteForum) {
		return forumService.deleteForum(deleteForum);
	}
}
