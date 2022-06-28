package com.financemanager.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.financemanager.configuration.TestDBConfiguration;
import com.financemanager.dao.MonthAnalyticsDao;
import com.financemanager.dao.impl.MonthAnalyticsDaoImpl;
import com.financemanager.demo.site.entity.MonthAnalyticsView;

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
    void returnCorrectAnalyticsByYear() {
        List<MonthAnalyticsView> expectedList = getMonthAnalytics();
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
        List<MonthAnalyticsView> expectedList = getMonthAnalytics();
        expectedList.get(0).setSum(22.9);
        expectedList.get(1).setSum(22.9);
        expectedList.get(5).setSum(22.9);
        assertEquals(expectedList, analyticsDao.getMonthStatistics(1, 2, null));
    }
    
    private static List<MonthAnalyticsView> getMonthAnalytics(){
        return Arrays.asList(
                new MonthAnalyticsView(1, "Січень", 0.0),
                new MonthAnalyticsView(2, "Лютий", 0.0),
                new MonthAnalyticsView(3, "Березень", 0.0),
                new MonthAnalyticsView(4, "Квітень", 0.0),
                new MonthAnalyticsView(5, "Травень", 0.0),
                new MonthAnalyticsView(6, "Червень", 0.0),
                new MonthAnalyticsView(7, "Липень", 0.0),
                new MonthAnalyticsView(8, "Серпень", 0.0),
                new MonthAnalyticsView(9, "Вересень", 0.0),
                new MonthAnalyticsView(10, "Жовтень", 0.0),
                new MonthAnalyticsView(11, "Листопад", 0.0),
                new MonthAnalyticsView(12, "Грудень", 0.0)
                );
    }

}
