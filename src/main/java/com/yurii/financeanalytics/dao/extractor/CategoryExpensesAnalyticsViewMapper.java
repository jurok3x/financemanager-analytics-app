package com.yurii.financeanalytics.dao.extractor;

import com.yurii.financeanalytics.entity.CategoryExpensesAnalyticsView;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CategoryExpensesAnalyticsViewMapper implements RowMapper<CategoryExpensesAnalyticsView> {

    @Override
    public CategoryExpensesAnalyticsView mapRow(ResultSet rs, int rowNum) throws SQLException {
        CategoryExpensesAnalyticsView view = new CategoryExpensesAnalyticsView();
        view.setName(rs.getString("name"));
        view.setCount(rs.getInt("count"));
        view.setSum(rs.getDouble("sum"));
        return view;
    }

}
