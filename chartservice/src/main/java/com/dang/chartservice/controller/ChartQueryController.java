package com.dang.chartservice.controller;

import com.dang.chartservice.entity.Chart;
import com.dang.chartservice.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chart")
public class ChartQueryController {

    @Autowired
    private ChartService chartService;

    @GetMapping("/get_chart")
    List<Chart> getChart() {
        return chartService.getChart();
    }
}
