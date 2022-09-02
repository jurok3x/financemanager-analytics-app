package com.yurii.financeanalytics.dao;

import com.yurii.financeanalytics.entity.view.MonthAnalyticsView;

import java.util.List;

public interface MonthAnalyticsDao {
    
    List<MonthAnalyticsView> getMonthExpensesAnalytics(Integer userId, Integer categoryId, Integer year);
    List<MonthAnalyticsView> getMonthIncomesAnalytics(Integer userId, Integer year);
    List<MonthAnalyticsView> getMonthBalanceAnalytics(Integer userId, Integer year);

}
