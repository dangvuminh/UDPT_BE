package com.dang.tagservice.service;

import com.dang.tagservice.entity.Tag;
import com.dang.tagservice.entity.TagResponse;
import com.dang.tagservice.tagRepository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public TagResponse createTags(Tag tag) {
        List<Tag> existingList = tagRepository.findAll();
        for(Tag item:existingList) {
            if(item.getTagName().equalsIgnoreCase(tag.getTagName())) {
                return new TagResponse("invalid tag","This tag can not be used",403);
            }
        }
        tagRepository.save(tag);
        return new TagResponse("tag created","This tag can be used",204);
    }

    public TagResponse isTagValid(List<Tag> tagList) {
       List<Tag> existingTags = tagRepository.findAll();
       Integer countValid = 0;
       for(int i = 0; i < tagList.size() ; i++) {
           for(int j = 0 ; j < existingTags.size(); j++) {
               if(tagList.get(i).getTagName().equalsIgnoreCase(existingTags.get(j).getTagName())) {
                   countValid ++;
               }
           }
           if(countValid == tagList.size()) {
               return new TagResponse("invalid tags","One or More tags are not created yet by admin",403);
           }
       }
        return new TagResponse("valid tags","Tags are valid to use",204);
    }
}
