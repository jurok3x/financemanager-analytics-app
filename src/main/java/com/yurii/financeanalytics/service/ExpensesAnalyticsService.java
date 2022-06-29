package com.yurii.financeanalytics.service;

import com.yurii.financeanalytics.entity.CategoryExpensesAnalyticsView;
import com.yurii.financeanalytics.entity.ExpensesAnalyticsView;

import java.util.List;

public interface ExpensesAnalyticsService {
    
    List<CategoryExpensesAnalyticsView> getAnalyticsByCategories(Integer userId, Integer month, Integer year);
    
    List<ExpensesAnalyticsView> getPopularExpensesAnalytics(Integer userId, Integer categoryId, Integer month, Integer year, Integer offset, Integer limit);

}
