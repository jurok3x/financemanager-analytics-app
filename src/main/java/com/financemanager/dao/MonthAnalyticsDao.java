package com.financemanager.dao;

import com.financemanager.demo.site.entity.MonthAnalyticsView;

import java.util.List;

public interface MonthAnalyticsDao {
    
    List<MonthAnalyticsView> getMonthStatistics(Integer userId, Integer categoryId, Integer year);

}
