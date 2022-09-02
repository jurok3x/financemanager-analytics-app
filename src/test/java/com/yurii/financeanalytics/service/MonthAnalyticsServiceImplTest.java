package com.yurii.financeanalytics.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.yurii.financeanalytics.dao.MonthAnalyticsDao;
import com.yurii.financeanalytics.entity.view.MonthAnalyticsView;
import com.yurii.financeanalytics.service.impl.MonthAnalyticsServiceImpl;

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
class MonthAnalyticsServiceImplTest {
    
    @Mock
    private MonthAnalyticsDao analyticsDao;
    
    @InjectMocks
    private MonthAnalyticsServiceImpl analyticsService;

    @Test
    void whenGetMonthExpensesAnalytics_thenReturnCorrectExpensesList() {
        List<MonthAnalyticsView> expectedList = getMonthAnalytics();
        given(analyticsDao.getMonthExpensesAnalytics(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt())).willReturn(expectedList);
        assertEquals(expectedList, analyticsService.getMonthExpensesAnalytics(1, 1, 2022));
        verify(analyticsDao).getMonthExpensesAnalytics(1, 1, 2022);
    }
    
    @Test
    void whenGetMonthIncomesAnalytics_thenReturnCorrectExpensesList() {
        List<MonthAnalyticsView> expectedList = getMonthAnalytics();
        given(analyticsDao.getMonthIncomesAnalytics(Mockito.anyInt(), Mockito.anyInt())).willReturn(expectedList);
        assertEquals(expectedList, analyticsService.getMonthIncomesAnalytics(1, 2022));
        verify(analyticsDao).getMonthIncomesAnalytics(1, 2022);
    }
    
    @Test
    void whenGetMonthBalanceAnalytics_thenReturnCorrectExpensesList() {
        List<MonthAnalyticsView> expectedList = getMonthAnalytics();
        given(analyticsDao.getMonthBalanceAnalytics(Mockito.anyInt(), Mockito.anyInt())).willReturn(expectedList);
        assertEquals(expectedList, analyticsService.getMonthBalanceAnalytics(1, 2022));
        verify(analyticsDao).getMonthBalanceAnalytics(1, 2022);
    }
    
    @AfterEach
    void tearDown(){
        verifyNoMoreInteractions(analyticsDao);
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
