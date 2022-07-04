package com.yurii.financeanalytics.dao;

import com.yurii.financeanalytics.entity.view.MonthExpensesAnalyticsView;

import java.util.List;

public interface MonthExpensesAnalyticsDao {
    
    List<MonthExpensesAnalyticsView> getMonthStatistics(Integer userId, Integer categoryId, Integer year);

}
