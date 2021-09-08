package com.dang.tagservice.controller;

import com.dang.tagservice.entity.Tag;
import com.dang.tagservice.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagQueryController {

    @Autowired
    private TagService tagService;

    @GetMapping("/get_tags")
   public List<Tag> getTags(){
       return tagService.getTags();
   }
}
