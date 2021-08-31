package com.dang.tagservice.controller;

import com.dang.tagservice.entity.Tag;
import com.dang.tagservice.entity.TagResponse;
import com.dang.tagservice.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagCommandController {
    @Autowired
    TagService tagService;

    @PostMapping("/create_tags")
    TagResponse createTag(@RequestBody Tag tag) {
        return tagService.createTags(tag);
    }

    @PostMapping("/is_tag_valid")
    TagResponse isTagValid(@RequestBody List<Tag> tagList) {
        return tagService.isTagValid(tagList);
    }
}
