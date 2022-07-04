package com.yurii.financeanalytics.dao.extractor;

import com.yurii.financeanalytics.entity.view.MonthExpensesAnalyticsView;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MonthExpensesAnalyticsViewMapper implements RowMapper<MonthExpensesAnalyticsView>{

    @Override
    public MonthExpensesAnalyticsView mapRow(ResultSet rs, int rowNum) throws SQLException {
        MonthExpensesAnalyticsView view = new MonthExpensesAnalyticsView();
        view.setMonthId(rs.getInt("id"));
        view.setMonthName(rs.getString("name"));
        view.setSum(rs.getDouble("sum"));
        return view;
    }

}
