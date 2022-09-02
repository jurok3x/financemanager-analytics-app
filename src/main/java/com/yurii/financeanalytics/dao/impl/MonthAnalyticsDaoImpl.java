package com.yurii.financeanalytics.dao.impl;

import com.yurii.financeanalytics.dao.MonthAnalyticsDao;
import com.yurii.financeanalytics.entity.view.MonthAnalyticsView;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@PropertySource(value = "classpath:/db/analytics/analytics_queries.properties")
public class MonthAnalyticsDaoImpl implements MonthAnalyticsDao {
    
    private final RowMapper<MonthAnalyticsView> analyticsViewMapper;
    private final NamedParameterJdbcTemplate template;
    
    @Value(value = "${get.month_expenses_analytics}")
    private String monthExpensesAnalyticsQuery;
    @Value(value = "${get.month_incomes_analytics}")
    private String monthIncomesAnalyticsQuery;
    @Value(value = "${get.month_balance_analytics}")
    private String monthBalanceAnalyticsQuery;

    @Override
    public List<MonthAnalyticsView> getMonthExpensesAnalytics(Integer userId, Integer categoryId, Integer year) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", userId);
        params.addValue("category_id", categoryId);
        params.addValue("year", year);
        return template.query(monthExpensesAnalyticsQuery, params, analyticsViewMapper);
    }

    @Override
    public List<MonthAnalyticsView> getMonthIncomesAnalytics(Integer userId, Integer year) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", userId);
        params.addValue("year", year);
        return template.query(monthIncomesAnalyticsQuery, params, analyticsViewMapper);
    }

    @Override
    public List<MonthAnalyticsView> getMonthBalanceAnalytics(Integer userId, Integer year) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", userId);
        params.addValue("year", year);
        return template.query(monthBalanceAnalyticsQuery, params, analyticsViewMapper);
    }

}
