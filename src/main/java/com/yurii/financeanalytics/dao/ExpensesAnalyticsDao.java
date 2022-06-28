package com.yurii.financeanalytics.dao;

import com.yurii.financeanalytics.entity.ExpensesAnalyticsView;

import java.util.List;

public interface ExpensesAnalyticsDao {
    
    List<ExpensesAnalyticsView> getCategoriesAnalytics(Integer userId, Integer month, Integer year);
    
    List<ExpensesAnalyticsView> getPopularItemsAnalytics(Integer userId, Integer categoryId, Integer month, Integer year, Integer offset, Integer limit);
}
