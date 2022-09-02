package com.yurii.financeanalytics.dao.impl;

import com.yurii.financeanalytics.dao.ExpensesAnalyticsDao;
import com.yurii.financeanalytics.entity.payload.DatePart;
import com.yurii.financeanalytics.entity.view.CategoryExpensesAnalyticsView;
import com.yurii.financeanalytics.entity.view.ExpensesAnalyticsView;

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
public class ExpensesAnalyticsDaoImpl implements ExpensesAnalyticsDao{
    
    private final RowMapper<ExpensesAnalyticsView> analyticsViewMapper;
    private final RowMapper<CategoryExpensesAnalyticsView> categoryAnalyticsViewMapper;
    private final NamedParameterJdbcTemplate template;
    
    @Value(value = "${get.category_analytics}")
    private String categoryAnalyticsQuery;
    
    @Value(value = "${get.most_poular_expenses_analytics}")
    private String popularItemsAnalyticsQuery;
    
    @Value(value = "${get.all_active_years}")
    private String activeYearsQuery;

    @Override
    public List<CategoryExpensesAnalyticsView> getAnalyticsByCategories(Integer userId, DatePart datePart) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", userId);
        params.addValue("month", datePart.getMonth());
        params.addValue("year", datePart.getYear());
        return template.query(categoryAnalyticsQuery, params, categoryAnalyticsViewMapper);
    }

    @Override
    public List<ExpensesAnalyticsView> getPopularExpensesAnalytics(Integer userId, Integer categoryId, DatePart datePart, Integer limit) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", userId);
        params.addValue("category_id", categoryId);
        params.addValue("month", datePart.getMonth());
        params.addValue("year", datePart.getYear());
        params.addValue("limit", limit);
        return template.query(popularItemsAnalyticsQuery, params, analyticsViewMapper);
    }

}
