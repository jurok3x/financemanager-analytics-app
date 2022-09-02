package com.yurii.financeanalytics.dao;

import static org.junit.jupiter.api.Assertions.*;

import com.yurii.financeanalytics.configuration.TestDBConfiguration;
import com.yurii.financeanalytics.dao.impl.MonthAnalyticsDaoImpl;
import com.yurii.financeanalytics.entity.view.MonthAnalyticsView;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = {TestDBConfiguration.class, MonthAnalyticsDaoImpl.class })
class MonthAnalyticsDaoImplTest {
    
    @Autowired
    MonthAnalyticsDao analyticsDao;

    @Test
    void returnCorrectExpensesAnalyticsByYear() {
        List<MonthAnalyticsView> expectedList = getMonthAnalytics();
        expectedList.get(0).setSum(22.9);
        expectedList.get(1).setSum(22.9);
        expectedList.get(2).setSum(45.9);
        expectedList.get(3).setSum(22.9);
        expectedList.get(4).setSum(122.9);
        expectedList.get(5).setSum(91.7);
        assertEquals(expectedList, analyticsDao.getMonthExpensesAnalytics(1, null, 2022));
    }
    
    @Test
    void returnCorrectExpensesAnalyticsByCategory() {
        List<MonthAnalyticsView> expectedList = getMonthAnalytics();
        expectedList.get(0).setSum(22.9);
        expectedList.get(1).setSum(22.9);
        expectedList.get(5).setSum(22.9);
        assertEquals(expectedList, analyticsDao.getMonthExpensesAnalytics(1, 2, null));
    }
    
    @Test
    void returnCorrectIncomesAnalytics() {
        List<MonthAnalyticsView> expectedList = getMonthAnalytics();
        expectedList.get(0).setSum(1000.0);
        expectedList.get(1).setSum(1000.0);
        expectedList.get(2).setSum(1000.0);
        expectedList.get(3).setSum(1000.0);
        expectedList.get(4).setSum(1000.0);
        expectedList.get(5).setSum(1000.0);
        assertEquals(expectedList, analyticsDao.getMonthIncomesAnalytics(1, 2022));
    }
    
    @Test
    void returnCorrectBalanceAnalytics() {
        List<MonthAnalyticsView> expectedList = getMonthAnalytics();
        expectedList.get(0).setSum(977.1);
        expectedList.get(1).setSum(977.1);
        expectedList.get(2).setSum(954.1);
        expectedList.get(3).setSum(977.1);
        expectedList.get(4).setSum(877.1);
        expectedList.get(5).setSum(908.3);
        assertEquals(expectedList, analyticsDao.getMonthBalanceAnalytics(1, 2022));
    }
    
    private static List<MonthAnalyticsView> getMonthAnalytics(){
        return Arrays.asList(
                new MonthAnalyticsView(1, "January", 0.0),
                new MonthAnalyticsView(2, "February", 0.0),
                new MonthAnalyticsView(3, "March", 0.0),
                new MonthAnalyticsView(4, "April", 0.0),
                new MonthAnalyticsView(5, "May", 0.0),
                new MonthAnalyticsView(6, "June", 0.0),
                new MonthAnalyticsView(7, "July", 0.0),
                new MonthAnalyticsView(8, "August", 0.0),
                new MonthAnalyticsView(9, "September", 0.0),
                new MonthAnalyticsView(10, "October", 0.0),
                new MonthAnalyticsView(11, "November", 0.0),
                new MonthAnalyticsView(12, "December", 0.0)
                );
    }

}
