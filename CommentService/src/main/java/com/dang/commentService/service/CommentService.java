package com.dang.commentService.service;

import com.dang.commentService.entity.*;
import com.dang.commentService.repository.LikeCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dang.commentService.repository.CommentRepository;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class CommentService {
	@Autowired
	CommentRepository commentRepository;

	@Autowired
	LikeCommentRepository likeCommentRepository;

	@Autowired
	private RestTemplate restTemplate;

	private boolean doesUserLikeComment(Integer commentId, String userId, Integer forumId) {
		Optional<LikeComment> doesUserLikeComment = likeCommentRepository.findByCommentIdAndUserIdAndForumId(commentId,userId,forumId);
		if(doesUserLikeComment.isPresent()) {
			return true;
		}
		return false;
	}
	public CommentResponse createComment(Comment comment) {
		CommentResponse isUserExisted = restTemplate.getForObject("http://USER-SERVICE/api/user/validate/" + comment.getUserId(), CommentResponse.class);
		CommentResponse isForumExisted = restTemplate.getForObject("http://FORUM-SERVICE/api/forum/is_forum_existed/" + comment.getForumId(), CommentResponse.class);
		if(isUserExisted.getStatusCode() == 204 && isForumExisted.getStatusCode() == 204) {
			comment.setNum_of_likes(0);
			commentRepository.save(comment);
			return new CommentResponse("comment created","A new comment has been added",204);
		}
		return new CommentResponse("comment failed","This new comment can't be added due to invalid user or forum id",404);
	}

	public NumberOfComments countCommentByForum(Integer forumId) {

		return new NumberOfComments(commentRepository.countByForumIdEquals(forumId));
	}

	public CommentResponse likeComment(LikeComment likeComment) {
		if(doesUserLikeComment(likeComment.getCommentId(),likeComment.getUserId(),likeComment.getForumId()) == false) {
			CommentResponse isUserExisted = restTemplate.getForObject("http://USER-SERVICE/api/user/validate/" + likeComment.getUserId(), CommentResponse.class);
			CommentResponse isForumExisted = restTemplate.getForObject("http://FORUM-SERVICE/api/forum/is_forum_existed/" + likeComment.getForumId(), CommentResponse.class);

			if(isUserExisted.getStatusCode() == 204 && isForumExisted.getStatusCode() == 204) {
				likeComment.setLiked(true);
				likeCommentRepository.save(likeComment);
				return new CommentResponse("liked comment","You have liked this comment",204);
			} else {
				return new CommentResponse("failed to like","User or forum or comment has not been not existing",404);
			}
		} else {
			Optional<LikeComment> existing = likeCommentRepository.findByCommentIdAndUserIdAndForumId(likeComment.getCommentId(),likeComment.getUserId(),likeComment.getForumId());
			likeCommentRepository.updateLikeComment(likeComment.getCommentId(),likeComment.getUserId(),likeComment.getForumId(),!existing.get().isLiked());
			if(existing.get().isLiked() == false)
				return new CommentResponse("liked comment","You have liked this comment",204);
				return new CommentResponse("unliked comment","You have unliked this comment",204);
		}
	}

	public CommentResponse deleteComment(DeleteComment deleteComment) {

		Optional<Comment> isCommentExisted = commentRepository.findByCommentIdAndUserIdAndForumId(deleteComment.getCommentId(),deleteComment.getUserId(),deleteComment.getForumId());
		if(isCommentExisted.isPresent()) {
			commentRepository.deleteByForumIdAndUserIdAndCommentId(deleteComment.getForumId(),deleteComment.getUserId(),deleteComment.getCommentId());
			return new CommentResponse("deleted","This comment has been deleted",204);
		}
		return new CommentResponse("fail to delete","This comment can't be deleted.One ore more input field is invalid",403);
	}
}
