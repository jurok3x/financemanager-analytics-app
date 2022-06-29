package com.yurii.financeanalytics.service.impl;

import com.yurii.financeanalytics.dao.ExpensesAnalyticsDao;
import com.yurii.financeanalytics.entity.CategoryExpensesAnalyticsView;
import com.yurii.financeanalytics.entity.ExpensesAnalyticsView;
import com.yurii.financeanalytics.service.ExpensesAnalyticsService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExpensesAnalyticsServiceImpl implements ExpensesAnalyticsService {
    
    private ExpensesAnalyticsDao analyticsDao;

    @Override
    public List<CategoryExpensesAnalyticsView> getAnalyticsByCategories(Integer userId, Integer month, Integer year) {
        return analyticsDao.getAnalyticsByCategories(userId, month, year);
    }

    @Override
    public List<ExpensesAnalyticsView> getPopularExpensesAnalytics(Integer userId, Integer categoryId,
            Integer month, Integer year, Integer offset, Integer limit) {
        return analyticsDao.getPopularExpensesAnalytics(userId, categoryId, month, year, offset, limit);
    }

}
