package com.dang.forumService.entity;

import javax.persistence.*;

@Entity
@Table(name="Like_Forum")
public class LikeForum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="like_forum_id")
    private Integer likeForumId;

    @Column(name="user_id_fk")
    private String userId;

    @Column(name="forum_id_fk")
    private Integer forumId;

    @Column(name="is_liked")
    private boolean isLiked;

    public LikeForum() {
    }

    public Integer getLikeForumId() {
        return likeForumId;
    }

    public void setLikeForumId(Integer likeForumId) {
        this.likeForumId = likeForumId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getForumId() {
        return forumId;
    }

    public void setForumId(Integer forumId) {
        this.forumId = forumId;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
