package com.yurii.financeanalytics.dao;

import static org.junit.jupiter.api.Assertions.*;

import com.yurii.financeanalytics.configuration.TestDBConfiguration;
import com.yurii.financeanalytics.dao.impl.ExpensesAnalyticsDaoImpl;
import com.yurii.financeanalytics.entity.view.CategoryExpensesAnalyticsView;
import com.yurii.financeanalytics.entity.view.ExpensesAnalyticsView;

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
    void returnCorrectCategoryAnalyticsAllTime() {
        List<CategoryExpensesAnalyticsView> expectedList = Arrays.asList(
                new CategoryExpensesAnalyticsView("Transport", 1, 122.9),
                new CategoryExpensesAnalyticsView("Food", 3, 68.7),
                new CategoryExpensesAnalyticsView("Medicine", 2, 91.8),
                new CategoryExpensesAnalyticsView("Goods", 2, 45.8),
                new CategoryExpensesAnalyticsView("Living", 1, 1800.9)
                );
        assertEquals(expectedList, analyticsDao.getAnalyticsByCategories(1, null, null));
    }
    
    @Test
    void returnCorrectCategoryAnalyticsByMonth() {
        List<CategoryExpensesAnalyticsView> expectedList = Arrays.asList(
                new CategoryExpensesAnalyticsView("Transport", 0, 0.0),
                new CategoryExpensesAnalyticsView("Food", 1, 22.9),
                new CategoryExpensesAnalyticsView("Medicine", 1, 45.9),
                new CategoryExpensesAnalyticsView("Goods", 1, 22.9),
                new CategoryExpensesAnalyticsView("Living", 1, 1800.9)
                );
        assertEquals(expectedList, analyticsDao.getAnalyticsByCategories(1, 6, null));
    }
    
    @Test
    void returnCorrectCategoryAnalyticsByYear() {
        List<CategoryExpensesAnalyticsView> expectedList = Arrays.asList(
                new CategoryExpensesAnalyticsView("Transport", 0, 0.0),
                new CategoryExpensesAnalyticsView("Food", 0, 0.0),
                new CategoryExpensesAnalyticsView("Medicine", 0, 0.0),
                new CategoryExpensesAnalyticsView("Goods", 0, 0.0),
                new CategoryExpensesAnalyticsView("Living", 1, 1800.9)
                );
        assertEquals(expectedList, analyticsDao.getAnalyticsByCategories(1, null, 2021));
    }
    
    @Test
    void returnCorrectCategoryAnalyticsByMonthAndYear() {
        List<CategoryExpensesAnalyticsView> expectedList = Arrays.asList(
                new CategoryExpensesAnalyticsView("Transport", 0, 0.0),
                new CategoryExpensesAnalyticsView("Food", 1, 22.9),
                new CategoryExpensesAnalyticsView("Medicine", 1, 45.9),
                new CategoryExpensesAnalyticsView("Goods", 1, 22.9),
                new CategoryExpensesAnalyticsView("Living", 0, 0.0)
                );
        assertEquals(expectedList, analyticsDao.getAnalyticsByCategories(1, 6, 2022));
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
        assertEquals(expectedList, analyticsDao.getPopularExpensesAnalytics(1, null, null, null, 0, 5));
    }
    
    @Test
    void returnCorrectPopularItemsAllTimeSingleCategory() {
        List<ExpensesAnalyticsView> expectedList = Arrays.asList(
                new ExpensesAnalyticsView("Taxy", 1, 122.9)
                );
        assertEquals(expectedList, analyticsDao.getPopularExpensesAnalytics(1, 1, null, null, 0, 5));
    }
    
    @Test
    void returnCorrectPopularItemsByDate() {
        List<ExpensesAnalyticsView> expectedList = Arrays.asList(
                new ExpensesAnalyticsView("Mezym", 1, 45.9),
                new ExpensesAnalyticsView("Scissors", 1, 22.9),
                new ExpensesAnalyticsView("Ketchup", 1, 22.9)
                );
        assertEquals(expectedList, analyticsDao.getPopularExpensesAnalytics(1, null, 6, 2022, 0, 5));
    }

}
