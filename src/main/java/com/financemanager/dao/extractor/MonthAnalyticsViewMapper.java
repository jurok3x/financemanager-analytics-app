package com.financemanager.dao.extractor;

import com.financemanager.demo.site.entity.MonthAnalyticsView;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

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
