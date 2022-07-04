package com.yurii.financeanalytics.service;

import com.yurii.financeanalytics.entity.view.CategoryExpensesAnalyticsView;
import com.yurii.financeanalytics.entity.view.ExpensesAnalyticsView;

import java.util.List;

public interface ExpensesAnalyticsService {
    
    List<CategoryExpensesAnalyticsView> getAnalyticsByCategories(Integer userId, Integer month, Integer year);
    
    List<ExpensesAnalyticsView> getPopularExpensesAnalytics(Integer userId, Integer categoryId, Integer month, Integer year, Integer offset, Integer limit);

}
