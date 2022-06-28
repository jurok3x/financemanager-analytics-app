package com.yurii.financeanalytics.dao;

import static org.junit.jupiter.api.Assertions.*;

import com.yurii.financeanalytics.configuration.TestDBConfiguration;
import com.yurii.financeanalytics.dao.impl.ExpensesAnalyticsDaoImpl;
import com.yurii.financeanalytics.entity.ExpensesAnalyticsView;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = {TestDBConfiguration.class, ExpensesAnalyticsDaoImpl.class })
class ExpensesAnalyticsDaoImplTest {
    
    @Autowired
    private ExpensesAnalyticsDao analyticsDao;

    @Test
    void returnCorrectCategoryCountAllTime() {
        List<ExpensesAnalyticsView> expectedList = Arrays.asList(
                new ExpensesAnalyticsView("Transport", 1, 122.9),
                new ExpensesAnalyticsView("Food", 3, 68.7),
                new ExpensesAnalyticsView("Medicine", 2, 91.8),
                new ExpensesAnalyticsView("Goods", 2, 45.8),
                new ExpensesAnalyticsView("Living", 1, 1800.9)
                );
        assertEquals(expectedList, analyticsDao.getCategoriesAnalytics(1, null, null));
    }
    
    @Test
    void returnCorrectCategoryCountAllByMonth() {
        List<ExpensesAnalyticsView> expectedList = Arrays.asList(
                new ExpensesAnalyticsView("Transport", 0, 0.0),
                new ExpensesAnalyticsView("Food", 1, 22.9),
                new ExpensesAnalyticsView("Medicine", 1, 45.9),
                new ExpensesAnalyticsView("Goods", 1, 22.9),
                new ExpensesAnalyticsView("Living", 1, 1800.9)
                );
        assertEquals(expectedList, analyticsDao.getCategoriesAnalytics(1, 6, null));
    }
    
    @Test
    void returnCorrectCategoryCountAllByYear() {
        List<ExpensesAnalyticsView> expectedList = Arrays.asList(
                new ExpensesAnalyticsView("Transport", 0, 0.0),
                new ExpensesAnalyticsView("Food", 0, 0.0),
                new ExpensesAnalyticsView("Medicine", 0, 0.0),
                new ExpensesAnalyticsView("Goods", 0, 0.0),
                new ExpensesAnalyticsView("Living", 1, 1800.9)
                );
        assertEquals(expectedList, analyticsDao.getCategoriesAnalytics(1, null, 2021));
    }
    
    @Test
    void returnCorrectCategoryCountAllByMonthAndYear() {
        List<ExpensesAnalyticsView> expectedList = Arrays.asList(
                new ExpensesAnalyticsView("Transport", 0, 0.0),
                new ExpensesAnalyticsView("Food", 1, 22.9),
                new ExpensesAnalyticsView("Medicine", 1, 45.9),
                new ExpensesAnalyticsView("Goods", 1, 22.9),
                new ExpensesAnalyticsView("Living", 0, 0.0)
                );
        assertEquals(expectedList, analyticsDao.getCategoriesAnalytics(1, 6, 2022));
    }
    
    @Test
    void returnCorrectPopularItemsAllTimeAndCategories() {
        List<ExpensesAnalyticsView> expectedList = Arrays.asList(
                new ExpensesAnalyticsView("Ketchup", 3, 68.7),
                new ExpensesAnalyticsView("Mezym", 2, 91.8),
                new ExpensesAnalyticsView("Scissors", 2, 45.8),
                new ExpensesAnalyticsView("Room", 1, 1800.9),
                new ExpensesAnalyticsView("Taxy", 1, 122.9)
                );
        assertEquals(expectedList, analyticsDao.getPopularItemsAnalytics(1, null, null, null, 0, 5));
    }
    
    @Test
    void returnCorrectPopularItemsAllTimeSingleCategory() {
        List<ExpensesAnalyticsView> expectedList = Arrays.asList(
                new ExpensesAnalyticsView("Taxy", 1, 122.9)
                );
        assertEquals(expectedList, analyticsDao.getPopularItemsAnalytics(1, 1, null, null, 0, 5));
    }
    
    @Test
    void returnCorrectPopularItemsByDate() {
        List<ExpensesAnalyticsView> expectedList = Arrays.asList(
                new ExpensesAnalyticsView("Mezym", 1, 45.9),
                new ExpensesAnalyticsView("Scissors", 1, 22.9),
                new ExpensesAnalyticsView("Ketchup", 1, 22.9)
                );
        assertEquals(expectedList, analyticsDao.getPopularItemsAnalytics(1, null, 6, 2022, 0, 5));
    }

}
