package com.dang.chartservice.repository;

import com.dang.chartservice.entity.Chart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChartRepository extends JpaRepository<Chart,Integer> {
}
