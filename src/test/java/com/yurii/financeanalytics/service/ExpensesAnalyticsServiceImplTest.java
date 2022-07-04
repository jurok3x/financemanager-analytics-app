package com.yurii.financeanalytics.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.yurii.financeanalytics.dao.ExpensesAnalyticsDao;
import com.yurii.financeanalytics.entity.view.CategoryExpensesAnalyticsView;
import com.yurii.financeanalytics.entity.view.ExpensesAnalyticsView;
import com.yurii.financeanalytics.service.impl.ExpensesAnalyticsServiceImpl;

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
class ExpensesAnalyticsServiceImplTest {

    @Mock
    ExpensesAnalyticsDao analyticsDao;
    
    @InjectMocks
    ExpensesAnalyticsServiceImpl analyticsService;
    
    @Test
    void whenGetCategoryAnalytics_thenReturnCorrectList() {
        List<CategoryExpensesAnalyticsView> expectedList = getCategoryAnalytics();
        given(analyticsDao.getAnalyticsByCategories(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt())).willReturn(expectedList);
        assertEquals(expectedList, analyticsService.getAnalyticsByCategories(1, 12, 2022));
        verify(analyticsDao).getAnalyticsByCategories(1, 12, 2022);
    }
    
    @Test
    void whenGetPopularExpenses_thenReturnCorrectList() {
        List<ExpensesAnalyticsView> expectedList = getPopularExpensesAnalytics();
        given(analyticsDao.getPopularExpensesAnalytics(Mockito.anyInt(), Mockito.any(), Mockito.anyInt(),
                Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt())).willReturn(expectedList);
        assertEquals(expectedList, analyticsService.getPopularExpensesAnalytics(1, null, 12, 2022, 0, 5));
        verify(analyticsDao).getPopularExpensesAnalytics(1, null, 12, 2022, 0, 5);
    }
    
    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(analyticsDao);
    }
    
    private static List<CategoryExpensesAnalyticsView> getCategoryAnalytics() {
        return Arrays.asList(
                new CategoryExpensesAnalyticsView("Transport", 1, 122.9),
                new CategoryExpensesAnalyticsView("Food", 3, 68.7),
                new CategoryExpensesAnalyticsView("Medicine", 2, 91.8),
                new CategoryExpensesAnalyticsView("Goods", 2, 45.8),
                new CategoryExpensesAnalyticsView("Living", 1, 1800.9)
                );
    }
    
    private static List<ExpensesAnalyticsView> getPopularExpensesAnalytics() {
        return Arrays.asList(
                new ExpensesAnalyticsView("Ketchup", 3, 68.7),
                new ExpensesAnalyticsView("Mezym", 2, 91.8),
                new ExpensesAnalyticsView("Scissors", 2, 45.8),
                new ExpensesAnalyticsView("Room", 1, 1800.9),
                new ExpensesAnalyticsView("Taxy", 1, 122.9)
                );
    }

}
