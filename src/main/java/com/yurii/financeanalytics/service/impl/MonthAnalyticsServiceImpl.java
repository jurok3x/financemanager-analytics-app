package com.yurii.financeanalytics.service.impl;

import com.yurii.financeanalytics.dao.MonthAnalyticsDao;
import com.yurii.financeanalytics.entity.view.MonthAnalyticsView;
import com.yurii.financeanalytics.service.MonthAnalyticsService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MonthAnalyticsServiceImpl implements MonthAnalyticsService{
    
    private MonthAnalyticsDao analyticsDao;

    @Override
    public List<MonthAnalyticsView> getMonthExpensesAnalytics(Integer userId, Integer categoryId, Integer year) {
        return analyticsDao.getMonthExpensesAnalytics(userId, categoryId, year);
    }

    @Override
    public List<MonthAnalyticsView> getMonthIncomesAnalytics(Integer userId, Integer year) {
        return analyticsDao.getMonthIncomesAnalytics(userId, year);
    }

    @Override
    public List<MonthAnalyticsView> getMonthBalanceAnalytics(Integer userId, Integer year) {
        return analyticsDao.getMonthBalanceAnalytics(userId, year);
    }

}