package com.dang.tagservice.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tag_id")
    private Integer tagId;

    @NotNull
    @Column(name="tag_name")
    private String tagName;

    @Column(name="tag_discription")
    private String tagDiscription;

    public Tag() {
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagDiscription() {
        return tagDiscription;
    }

    public void setTagDiscription(String tagDiscription) {
        this.tagDiscription = tagDiscription;
    }
}
