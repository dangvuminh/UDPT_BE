package com.dang.forumService.controller;

import com.dang.forumService.entity.*;
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
    public List<ForumDisplay> getIllegalQuestionList(){
        return forumService.getIllegalQuestionList();
    }

    @GetMapping("/get_illegal_questions/{categoryId}")
    public List<ForumDisplay> getIllegalQuestionListByCategory(@PathVariable Integer categoryId){
        return forumService.getIllegalQuestionListByCategory(categoryId);
    }

    @GetMapping("/get_legal_questions")
    public List<ForumDisplay> getLegalQuestionList(){
        return forumService.getLegalQuestionList();
    }

    @GetMapping("/get_legal_questions/{categoryId}")
    public List<ForumDisplay> getLegalQuestionListByCategory(@PathVariable Integer categoryId){
        return forumService.getLegalQuestionListByCategory(categoryId);
    }

    @GetMapping("/is_forum_existed/{forum_id}")
    public ForumResponse isForumExisted(@PathVariable Integer forum_id){
       return forumService.isForumExisted(forum_id);
    }

    @GetMapping("/get_likes_of_forum/{forumId}")
    public Integer getLikeOfForum(@PathVariable Integer forumId){
        return forumService.getLikeOfForum(forumId);
    }

    @GetMapping("/get_likes_of_user/{userId}")
    public Integer getLikeOfUser(@PathVariable String userId){
        return forumService.getLikeOfUser(userId);
    }

    @GetMapping("/search_by_tags/{tagList}")
    public List<Forum> searchForumByTag(@PathVariable String tagList){
        return forumService.searchByTags(tagList);
    }

    @GetMapping("/get_categories")
    public List<Category> getCategories(){
        return forumService.getCategories();
    }

    @GetMapping("/get_forum_by_user/{userId}")
    public List<Forum> getForumByUser(@PathVariable String userId){
        return forumService.getForumByUser(userId);
    }

    @GetMapping("/get_favorite_forums/{forumId}/{userId}")
    public List<LikeForum> getFavoriteComments(@PathVariable Integer forumId, @PathVariable String userId){
        return forumService.getFavoriteForums(forumId,userId);
    }
}
