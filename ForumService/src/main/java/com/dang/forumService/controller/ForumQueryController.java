package com.dang.forumService.controller;

import com.dang.forumService.entity.Forum;
import com.dang.forumService.entity.ForumResponse;
import com.dang.forumService.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/forum")
public class ForumQueryController {
    @Autowired
    private ForumService forumService;

    @GetMapping("/get_illegal_questions")
    public List<Forum> getIllegalQuestionList(){
        return forumService.getIllegalQuestionList();
    }

    @GetMapping("/get_illegal_questions/{categoryId}")
    public List<Forum> getIllegalQuestionListByCategory(@PathVariable Integer categoryId){
        return forumService.getIllegalQuestionListByCategory(categoryId);
    }

    @GetMapping("/get_legal_questions")
    public List<Forum> getLegalQuestionList(){
        return forumService.getLegalQuestionList();
    }

    @GetMapping("/get_legal_questions/{categoryId}")
    public List<Forum> getLegalQuestionListByCategory(@PathVariable Integer categoryId){
        return forumService.getLegalQuestionListByCategory(categoryId);
    }

    @GetMapping("/is_forum_existed/{forum_id}")
    public ForumResponse isForumExisted(@PathVariable Integer forum_id){
       return forumService.isForumExisted(forum_id);
    }
}
