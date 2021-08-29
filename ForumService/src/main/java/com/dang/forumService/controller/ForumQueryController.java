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
import java.util.Optional;

@RestController
@RequestMapping("/api/forum")
public class ForumQueryController {
    @Autowired
    ForumService forumService;

    @GetMapping("/get_questions")
    public List<Forum> getQuestionList(){
        return forumService.getQuestionList();
    }

    @GetMapping("/get_questions/{categoryId}")
    public List<Forum> getQuestionListByCategory(@PathVariable Integer categoryId){
        return forumService.getQuestionListByCategory(categoryId);
    }

    @GetMapping("/is_forum_existed/{forum_id}")
    public ForumResponse isForumExisted(@PathVariable Integer forum_id){
       return forumService.isCategoryExisted(forum_id);
    }
}
