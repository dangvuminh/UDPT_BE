package com.dang.chartservice.service;

import com.dang.chartservice.entity.Chart;
import com.dang.chartservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChartService {
    @Autowired
    private RestTemplate restTemplate;

    public List<Chart> getChart() {
        ResponseEntity<List<User>> userResponse =
                restTemplate.exchange("http://USER-SERVICE/api/user/get_users",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                        });
        List<User> userList = userResponse.getBody();
        List<Chart> chartList = userList.stream().map((u) -> {
           ResponseEntity<Integer> commentRes =
                   restTemplate.exchange("http://COMMENT-SERVICE/api/comment/get_likes_of_user/" + u.getUser_id(),
                           HttpMethod.GET, null, new ParameterizedTypeReference<Integer>() {
                           });
           Integer commentNumOfLikes = commentRes.getBody();
           ResponseEntity<Integer> forumRes =
                   restTemplate.exchange("http://FORUM-SERVICE/api/forum/get_likes_of_user/" + u.getUser_id(),
                           HttpMethod.GET, null, new ParameterizedTypeReference<Integer>() {
                           });
           Integer forumNumOfLikes = forumRes.getBody();
           Integer totalLikes = commentNumOfLikes + forumNumOfLikes;
           Double points = new Double(totalLikes*60)/100 + new Double(u.getNumOfComments()*40)/100 ;
           return new Chart(u.getUsername(),u.getNumOfComments(),totalLikes,points);
       }).collect(Collectors.toList());
        Collections.sort(chartList,Comparator.comparingDouble(Chart ::getPoints).reversed());
        return chartList;
    }
}
