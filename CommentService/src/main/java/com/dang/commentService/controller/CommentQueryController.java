package com.dang.commentService.controller;

import com.dang.commentService.entity.NumberOfComments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dang.commentService.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentQueryController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/count_comment_by_forum/{forum_id}")
    public NumberOfComments countComments(@PathVariable Integer forum_id) {
        return commentService.countCommentByForum(forum_id);
    }
}
