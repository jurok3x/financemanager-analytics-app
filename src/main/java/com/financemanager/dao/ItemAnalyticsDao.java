package com.financemanager.dao;

import com.financemanager.demo.site.entity.ItemsAnalyticsView;

import java.util.List;

public interface ItemAnalyticsDao {
    
    List<ItemsAnalyticsView> getCategoriesAnalytics(Integer userId, Integer month, Integer year);
    
    List<ItemsAnalyticsView> getPopularItemsAnalytics(Integer userId, Integer categoryId, Integer month, Integer year, Integer offset, Integer limit);
}
