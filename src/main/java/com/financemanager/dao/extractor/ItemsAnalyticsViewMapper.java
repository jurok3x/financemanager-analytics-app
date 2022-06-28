package com.financemanager.dao.extractor;

import com.financemanager.demo.site.entity.ItemsAnalyticsView;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ItemsAnalyticsViewMapper implements RowMapper<ItemsAnalyticsView> {

    @Override
    public ItemsAnalyticsView mapRow(ResultSet rs, int rowNum) throws SQLException {
        ItemsAnalyticsView view = new ItemsAnalyticsView();
        view.setName(rs.getString("name"));
        view.setCount(rs.getInt("count"));
        view.setSum(rs.getDouble("sum"));
        return view;
    }

}
