package com.yurii.financeanalytics.dao.impl;

import com.yurii.financeanalytics.dao.MonthExpensesAnalyticsDao;
import com.yurii.financeanalytics.entity.view.MonthExpensesAnalyticsView;

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
public class MonthExpensesAnalyticsDaoImpl implements MonthExpensesAnalyticsDao {
    
    private final RowMapper<MonthExpensesAnalyticsView> analyticsViewMapper;
    private final NamedParameterJdbcTemplate template;
    
    @Value(value = "${get.month_analytics}")
    private String monthAnalyticsQuery;

    @Override
    public List<MonthExpensesAnalyticsView> getMonthStatistics(Integer userId, Integer categoryId, Integer year) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", userId);
        params.addValue("category_id", categoryId);
        params.addValue("year", year);
        return template.query(monthAnalyticsQuery, params, analyticsViewMapper);
    }

}
