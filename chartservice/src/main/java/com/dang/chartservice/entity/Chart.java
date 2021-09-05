package com.dang.chartservice.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Chart")
public class Chart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="chart_id")
    private Integer chartId;

    @NotNull
    @Column(name="username")
    private String username;

    @Column(name="num_of_comments")
    private Integer numOfComments;

    @Column(name="num_of_likes")
    private Integer numOfLikes;

    @Column(name="points")
    private Double points;

    public Chart() {
    }

    public Chart(String username, Integer numOfComments, Integer numOfLikes, Double points) {
        this.chartId = chartId;
        this.username = username;
        this.numOfComments = numOfComments;
        this.numOfLikes = numOfLikes;
        this.points = points;
    }

    public Integer getChartId() {
        return chartId;
    }

    public void setChartId(Integer chartId) {
        this.chartId = chartId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getNumOfComments() {
        return numOfComments;
    }

    public void setNumOfComments(Integer numOfComments) {
        this.numOfComments = numOfComments;
    }

    public Integer getNumOfLikes() {
        return numOfLikes;
    }

    public void setNumOfLikes(Integer numOfLikes) {
        this.numOfLikes = numOfLikes;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }
}
