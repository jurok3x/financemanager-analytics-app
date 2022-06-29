package com.yurii.financeanalytics.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.yurii.financeanalytics.dao.MonthExpensesAnalyticsDao;
import com.yurii.financeanalytics.entity.MonthExpensesAnalyticsView;
import com.yurii.financeanalytics.service.impl.MonthExpensesAnalyticsServiceImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(value = { MockitoExtension.class })
class MonthExpensesAnalyticsServiceImplTest {
    
    @Mock
    private MonthExpensesAnalyticsDao analyticsDao;
    
    @InjectMocks
    private MonthExpensesAnalyticsServiceImpl analyticsService;

    @Test
    void whenGetMonthStatistics_returnCorrectExpensesList() {
        List<MonthExpensesAnalyticsView> expectedList = getMonthAnalytics();
        given(analyticsDao.getMonthStatistics(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt())).willReturn(expectedList);
        assertEquals(expectedList, analyticsService.getMonthAnalytics(1, 1, 2022));
        verify(analyticsDao).getMonthStatistics(1, 1, 2022);
    }
    
    @AfterEach
    void tearDown(){
        verifyNoMoreInteractions(analyticsDao);
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
