package com.dang.commentService.controller;

import com.dang.commentService.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dang.commentService.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentCommandController {
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/create_comment")
	public CommentResponse createComment(@RequestBody Comment comment) {
		return commentService.createComment(comment);
	}

	@PostMapping("/like_comment")
	public CommentResponse likeComment(@RequestBody LikeComment likeComment) {
		return commentService.likeComment(likeComment);
	}

	@DeleteMapping("/delete_comment")
	public CommentResponse deleteComment(@RequestBody DeleteComment deleteComment) {
		return commentService.deleteComment(deleteComment);
	}

	@PutMapping("/update_comment")
	public CommentResponse updateComment(@RequestBody UpdateComment updateComment) {
		return commentService.updateComment(updateComment);
	}
}
