package com.yurii.financeanalytics.service;

import com.yurii.financeanalytics.entity.payload.DatePart;
import com.yurii.financeanalytics.entity.view.CategoryExpensesAnalyticsView;
import com.yurii.financeanalytics.entity.view.ExpensesAnalyticsView;

import java.util.List;

public interface ExpensesAnalyticsService {
    
    List<CategoryExpensesAnalyticsView> getAnalyticsByCategories(Integer userId, DatePart datePart);
    
    List<ExpensesAnalyticsView> getPopularExpensesAnalytics(Integer userId, Integer categoryId, DatePart datePart, Integer limit);

}
