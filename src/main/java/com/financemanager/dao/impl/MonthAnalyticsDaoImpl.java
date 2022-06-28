package com.financemanager.dao.impl;

import com.financemanager.dao.MonthAnalyticsDao;
import com.financemanager.demo.site.entity.MonthAnalyticsView;

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
    
    @Value(value = "${get.month_analytics}")
    private String monthAnalyticsQuery;

    @Override
    public List<MonthAnalyticsView> getMonthStatistics(Integer userId, Integer categoryId, Integer year) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", userId);
        params.addValue("category_id", categoryId);
        params.addValue("year", year);
        return template.query(monthAnalyticsQuery, params, analyticsViewMapper);
    }

}
