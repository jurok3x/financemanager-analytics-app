package com.financemanager.dao;

import static org.junit.jupiter.api.Assertions.*;

import com.financemanager.configuration.TestDBConfiguration;
import com.financemanager.dao.impl.ItemAnalyticsDaoImpl;
import com.financemanager.demo.site.entity.ItemsAnalyticsView;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = {TestDBConfiguration.class, ItemAnalyticsDaoImpl.class })
class ItemAnalyticsDaoImplTest {
    
    @Autowired
    private ItemAnalyticsDao analyticsDao;

    @Test
    void returnCorrectCategoryCountAllTime() {
        List<ItemsAnalyticsView> expectedList = Arrays.asList(
                new ItemsAnalyticsView("Транспорт", 1, 122.9),
                new ItemsAnalyticsView("Їжа", 3, 68.7),
                new ItemsAnalyticsView("Медицина", 2, 91.8),
                new ItemsAnalyticsView("Товари", 2, 45.8),
                new ItemsAnalyticsView("Проживання", 1, 1800.9)
                );
        assertEquals(expectedList, analyticsDao.getCategoriesAnalytics(1, null, null));
    }
    
    @Test
    void returnCorrectCategoryCountAllByMonth() {
        List<ItemsAnalyticsView> expectedList = Arrays.asList(
                new ItemsAnalyticsView("Транспорт", 0, 0.0),
                new ItemsAnalyticsView("Їжа", 1, 22.9),
                new ItemsAnalyticsView("Медицина", 1, 45.9),
                new ItemsAnalyticsView("Товари", 1, 22.9),
                new ItemsAnalyticsView("Проживання", 1, 1800.9)
                );
        assertEquals(expectedList, analyticsDao.getCategoriesAnalytics(1, 6, null));
    }
    
    @Test
    void returnCorrectCategoryCountAllByYear() {
        List<ItemsAnalyticsView> expectedList = Arrays.asList(
                new ItemsAnalyticsView("Транспорт", 0, 0.0),
                new ItemsAnalyticsView("Їжа", 0, 0.0),
                new ItemsAnalyticsView("Медицина", 0, 0.0),
                new ItemsAnalyticsView("Товари", 0, 0.0),
                new ItemsAnalyticsView("Проживання", 1, 1800.9)
                );
        assertEquals(expectedList, analyticsDao.getCategoriesAnalytics(1, null, 2021));
    }
    
    @Test
    void returnCorrectCategoryCountAllByMonthAndYear() {
        List<ItemsAnalyticsView> expectedList = Arrays.asList(
                new ItemsAnalyticsView("Транспорт", 0, 0.0),
                new ItemsAnalyticsView("Їжа", 1, 22.9),
                new ItemsAnalyticsView("Медицина", 1, 45.9),
                new ItemsAnalyticsView("Товари", 1, 22.9),
                new ItemsAnalyticsView("Проживання", 0, 0.0)
                );
        assertEquals(expectedList, analyticsDao.getCategoriesAnalytics(1, 6, 2022));
    }
    
    @Test
    void returnCorrectPopularItemsAllTimeAndCategories() {
        List<ItemsAnalyticsView> expectedList = Arrays.asList(
                new ItemsAnalyticsView("Кетчуп", 3, 68.7),
                new ItemsAnalyticsView("Мезим", 2, 91.8),
                new ItemsAnalyticsView("Ножниці", 2, 45.8),
                new ItemsAnalyticsView("Квартплата", 1, 1800.9),
                new ItemsAnalyticsView("Таксі", 1, 122.9)
                );
        assertEquals(expectedList, analyticsDao.getPopularItemsAnalytics(1, null, null, null, 0, 5));
    }
    
    @Test
    void returnCorrectPopularItemsAllTimeSingleCategory() {
        List<ItemsAnalyticsView> expectedList = Arrays.asList(
                new ItemsAnalyticsView("Таксі", 1, 122.9)
                );
        assertEquals(expectedList, analyticsDao.getPopularItemsAnalytics(1, 1, null, null, 0, 5));
    }
    
    @Test
    void returnCorrectPopularItemsByDate() {
        List<ItemsAnalyticsView> expectedList = Arrays.asList(
                new ItemsAnalyticsView("Мезим", 1, 45.9),
                new ItemsAnalyticsView("Ножниці", 1, 22.9),
                new ItemsAnalyticsView("Кетчуп", 1, 22.9)
                );
        assertEquals(expectedList, analyticsDao.getPopularItemsAnalytics(1, null, 6, 2022, 0, 5));
    }

}
