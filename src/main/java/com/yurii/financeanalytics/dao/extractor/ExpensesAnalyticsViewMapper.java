package com.yurii.financeanalytics.dao.extractor;

import com.yurii.financeanalytics.entity.view.ExpensesAnalyticsView;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ExpensesAnalyticsViewMapper implements RowMapper<ExpensesAnalyticsView> {

    @Override
    public ExpensesAnalyticsView mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExpensesAnalyticsView view = new ExpensesAnalyticsView();
        view.setName(rs.getString("name"));
        view.setCount(rs.getInt("count"));
        view.setSum(rs.getDouble("sum"));
        return view;
    }

}
