package com.yurii.financeanalytics.service;

import com.yurii.financeanalytics.entity.view.MonthExpensesAnalyticsView;

import java.util.List;

public interface MonthExpensesAnalyticsService {
    
    List<MonthExpensesAnalyticsView> getMonthAnalytics(Integer userId, Integer categoryId, Integer year);

}
