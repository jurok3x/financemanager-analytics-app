package com.financemanager.dao.impl;

import com.financemanager.dao.ItemAnalyticsDao;
import com.financemanager.demo.site.entity.ItemsAnalyticsView;

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
public class ItemAnalyticsDaoImpl implements ItemAnalyticsDao{
    
    private final RowMapper<ItemsAnalyticsView> analyticsViewMapper;
    private final NamedParameterJdbcTemplate template;
    
    @Value(value = "${get.category_analytics}")
    private String categoryAnalyticsQuery;
    
    @Value(value = "${get.most_poular_items_analytics}")
    private String popularItemsAnalyticsQuery;
    
    @Value(value = "${get.all_active_years}")
    private String activeYearsQuery;

    @Override
    public List<ItemsAnalyticsView> getCategoriesAnalytics(Integer userId, Integer month, Integer year) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", userId);
        params.addValue("month", month);
        params.addValue("year", year);
        return template.query(categoryAnalyticsQuery, params, analyticsViewMapper);
    }

    @Override
    public List<ItemsAnalyticsView> getPopularItemsAnalytics(Integer userId, Integer categoryId, Integer month, Integer year,
            Integer offset, Integer limit) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", userId);
        params.addValue("category_id", categoryId);
        params.addValue("month", month);
        params.addValue("year", year);
        params.addValue("offset", offset);
        params.addValue("limit", limit);
        return template.query(popularItemsAnalyticsQuery, params, analyticsViewMapper);
    }

}
