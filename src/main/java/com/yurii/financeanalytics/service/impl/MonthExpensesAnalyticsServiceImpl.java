package com.yurii.financeanalytics.service.impl;

import com.yurii.financeanalytics.dao.MonthExpensesAnalyticsDao;
import com.yurii.financeanalytics.entity.view.MonthExpensesAnalyticsView;
import com.yurii.financeanalytics.service.MonthExpensesAnalyticsService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MonthExpensesAnalyticsServiceImpl implements MonthExpensesAnalyticsService{
    
    private MonthExpensesAnalyticsDao analyticsDao;

    @Override
    public List<MonthExpensesAnalyticsView> getMonthAnalytics(Integer userId, Integer categoryId, Integer year) {
        return analyticsDao.getMonthStatistics(userId, categoryId, year);
    }

}