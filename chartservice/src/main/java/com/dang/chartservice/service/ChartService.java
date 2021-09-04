package com.dang.chartservice.service;

import com.dang.chartservice.entity.Chart;
import com.dang.chartservice.entity.NumberOfLikes;
import com.dang.chartservice.entity.User;
import com.dang.chartservice.entity.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChartService {
    @Autowired
    private RestTemplate restTemplate;

    public List<Chart> getChart() {
       UserList res = restTemplate.getForObject("http://USER-SERVICE/api/user/get_users", UserList.class);
       List<User> userList = res.getUserList();
       return userList.stream().map((u) -> {
           NumberOfLikes commentNumOfLikes = restTemplate.getForObject("http://COMMENT-SERVICE/api/comment/get_likes_of_user" + u.getUser_id(),NumberOfLikes.class);
           NumberOfLikes forumNumOfLikes = restTemplate.getForObject("http://USER-SERVICE/api/comment/get_likes_of_user" + u.getUser_id(),NumberOfLikes.class);
           Integer totalLikes = commentNumOfLikes.getNumOfLikes() + forumNumOfLikes.getNumOfLikes();
           Integer points = totalLikes + u.getNumOfComments();
           return new Chart(u.getUsername(),u.getNumOfComments(),totalLikes,points);
       }).collect(Collectors.toList());
    }
}
