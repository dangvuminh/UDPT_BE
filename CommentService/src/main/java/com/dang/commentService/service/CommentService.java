package com.dang.commentService.service;

import com.dang.commentService.entity.CommentResponse;
import com.dang.commentService.entity.NumberOfComments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dang.commentService.entity.Comment;
import com.dang.commentService.repository.CommentRepository;
import org.springframework.web.client.RestTemplate;

@Service
public class CommentService {
	@Autowired
	CommentRepository commentRepository;

	@Autowired
	private RestTemplate restTemplate;

	public CommentResponse createComment(Comment comment) {
		CommentResponse isUserExisted = restTemplate.getForObject("http://USER-SERVICE/api/validate" + comment.getUser_id_fk(),CommentResponse.class);
		CommentResponse isForumExisted = restTemplate.getForObject("http://FORUM-SERVICE/api/is_forum_existed" + comment.getForum_id_fk(),CommentResponse.class);
		if(isForumExisted.getStatusCode() == 204 && isUserExisted.getStatusCode() == 204) {
			comment.setNum_of_likes(0);
			commentRepository.save(comment);
			return new CommentResponse("comment created","A new comment has been added",204);
		}
		return new CommentResponse("comment failed","This new comment can't be added due to invalid user or forum id",404);
	}

	public NumberOfComments countCommentByForum(Integer forumId) {

		return new NumberOfComments(commentRepository.countByForumIdEquals(forumId));
	}
}
