package com.financemanager.dao.impl;

import com.financemanager.dao.AnalyticsUtilsDao;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@PropertySource(value = "classpath:/db/analytics/analytics_queries.properties")
public class AnalyticsUtilsDaoImpl implements AnalyticsUtilsDao {
    
    private final NamedParameterJdbcTemplate template;
    
    @Value(value = "${get.all_active_years}")
    private String activeYearsQuery;

    @Override
    public List<Integer> getAllActiveYears(Integer userId) {
        SqlParameterSource param = new MapSqlParameterSource("user_id", userId);
        return template.queryForList(activeYearsQuery, param, Integer.class);
    }

}
