package com.dang.commentService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dang.commentService.entity.Comment;
import com.dang.commentService.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentCommandController {
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/create_comment")
	public void createComment(@RequestBody Comment comment) {
		commentService.createComment(comment);
	}
}
