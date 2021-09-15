package com.dang.commentService.controller;

import com.dang.commentService.entity.Comment;
import com.dang.commentService.entity.CommentDisplay;
import com.dang.commentService.entity.LikeComment;
import com.dang.commentService.entity.NumberOfComments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dang.commentService.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentQueryController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/count_comment_by_forum/{forum_id}")
    public NumberOfComments countComments(@PathVariable Integer forum_id) {
        return commentService.countCommentByForum(forum_id);
    }

    @GetMapping("/num_of_comments/{userId}")
    public Integer getNumOfCommentsByUser(@PathVariable String userId){
        return commentService.getNumOfCommentsByUser(userId);
    }

    @GetMapping("/get_likes_of_comment/{forumId}/{commentId}")
    public Integer getLikeOfComment(@PathVariable Integer forumId,@PathVariable Integer commentId){
        return commentService.getLikeOfComment(forumId,commentId);
    }

    @GetMapping("/get_likes_of_user/{userId}")
    public Integer getLikeOfUser(@PathVariable String userId){
        return commentService.getLikeOfUser(userId);
    }

    @GetMapping("/get_comments/{forumId}")
    public List<CommentDisplay> getComments(@PathVariable Integer forumId){
        return commentService.getComments(forumId);
    }

    @GetMapping("/get_favorite_comments/{forumId}/{userId}")
    public List<LikeComment> getFavoriteComments(@PathVariable Integer forumId,@PathVariable String userId){
        return commentService.getFavoriteComments(forumId,userId);
    }

}
