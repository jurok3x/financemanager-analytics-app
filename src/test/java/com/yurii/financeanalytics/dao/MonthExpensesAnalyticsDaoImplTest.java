package com.yurii.financeanalytics.dao;

import static org.junit.jupiter.api.Assertions.*;

import com.yurii.financeanalytics.configuration.TestDBConfiguration;
import com.yurii.financeanalytics.dao.impl.MonthExpensesAnalyticsDaoImpl;
import com.yurii.financeanalytics.entity.view.MonthExpensesAnalyticsView;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = {TestDBConfiguration.class, MonthExpensesAnalyticsDaoImpl.class })
class MonthExpensesAnalyticsDaoImplTest {
    
    @Autowired
    MonthExpensesAnalyticsDao analyticsDao;

    @Test
    void returnCorrectAnalyticsByYear() {
        List<MonthExpensesAnalyticsView> expectedList = getMonthAnalytics();
        expectedList.get(0).setSum(22.9);
        expectedList.get(1).setSum(22.9);
        expectedList.get(2).setSum(45.9);
        expectedList.get(3).setSum(22.9);
        expectedList.get(4).setSum(122.9);
        expectedList.get(5).setSum(91.7);
        assertEquals(expectedList, analyticsDao.getMonthStatistics(1, null, 2022));
    }
    

    @Test
    void returnCorrectAnalyticsByCategory() {
        List<MonthExpensesAnalyticsView> expectedList = getMonthAnalytics();
        expectedList.get(0).setSum(22.9);
        expectedList.get(1).setSum(22.9);
        expectedList.get(5).setSum(22.9);
        assertEquals(expectedList, analyticsDao.getMonthStatistics(1, 2, null));
    }
    
    private static List<MonthExpensesAnalyticsView> getMonthAnalytics(){
        return Arrays.asList(
                new MonthExpensesAnalyticsView(1, "January", 0.0),
                new MonthExpensesAnalyticsView(2, "February", 0.0),
                new MonthExpensesAnalyticsView(3, "March", 0.0),
                new MonthExpensesAnalyticsView(4, "April", 0.0),
                new MonthExpensesAnalyticsView(5, "May", 0.0),
                new MonthExpensesAnalyticsView(6, "June", 0.0),
                new MonthExpensesAnalyticsView(7, "July", 0.0),
                new MonthExpensesAnalyticsView(8, "August", 0.0),
                new MonthExpensesAnalyticsView(9, "September", 0.0),
                new MonthExpensesAnalyticsView(10, "October", 0.0),
                new MonthExpensesAnalyticsView(11, "November", 0.0),
                new MonthExpensesAnalyticsView(12, "December", 0.0)
                );
    }

}
