package com.yurii.financeanalytics.dao.extractor;

import com.yurii.financeanalytics.entity.view.MonthAnalyticsView;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MonthAnalyticsViewMapper implements RowMapper<MonthAnalyticsView>{

    @Override
    public MonthAnalyticsView mapRow(ResultSet rs, int rowNum) throws SQLException {
        MonthAnalyticsView view = new MonthAnalyticsView();
        view.setMonthId(rs.getInt("id"));
        view.setMonthName(rs.getString("name"));
        view.setSum(rs.getDouble("sum"));
        return view;
    }

}
