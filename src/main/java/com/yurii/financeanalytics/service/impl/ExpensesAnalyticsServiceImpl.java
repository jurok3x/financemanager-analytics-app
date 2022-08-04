package com.yurii.financeanalytics.service.impl;

import com.yurii.financeanalytics.dao.ExpensesAnalyticsDao;
import com.yurii.financeanalytics.entity.payload.DatePart;
import com.yurii.financeanalytics.entity.view.CategoryExpensesAnalyticsView;
import com.yurii.financeanalytics.entity.view.ExpensesAnalyticsView;
import com.yurii.financeanalytics.service.ExpensesAnalyticsService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExpensesAnalyticsServiceImpl implements ExpensesAnalyticsService {
    
    private ExpensesAnalyticsDao analyticsDao;

    @Override
    public List<CategoryExpensesAnalyticsView> getAnalyticsByCategories(Integer userId, DatePart datePart) {
        return analyticsDao.getAnalyticsByCategories(userId, datePart);
    }

    @Override
    public List<ExpensesAnalyticsView> getPopularExpensesAnalytics(Integer userId, Integer categoryId,
            DatePart datePart, Integer limit) {
        return analyticsDao.getPopularExpensesAnalytics(userId, categoryId, datePart, limit);
    }

}