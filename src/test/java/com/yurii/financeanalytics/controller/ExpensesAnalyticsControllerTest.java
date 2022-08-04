package com.yurii.financeanalytics.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yurii.financeanalytics.entity.payload.DatePart;
import com.yurii.financeanalytics.entity.view.CategoryExpensesAnalyticsView;
import com.yurii.financeanalytics.entity.view.ExpensesAnalyticsView;
import com.yurii.financeanalytics.service.ExpensesAnalyticsService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(value = ExpensesAnalyticsController.class, useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ExpensesAnalyticsController.class)})
@AutoConfigureMockMvc(addFilters = false)
class ExpensesAnalyticsControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ExpensesAnalyticsService analyticsService;
    
    private ObjectMapper mapper;
    
    @Test
    void whenGetCategoryAnalytics_thenReturnStatus200() throws Exception {
        mapper = new ObjectMapper();
        List<CategoryExpensesAnalyticsView> expectedList = getCategoryAnalytics();
        given(analyticsService.getAnalyticsByCategories(Mockito.anyInt(), Mockito.any(DatePart.class))).willReturn(expectedList);
        ResultActions result = mvc.perform(get("/api/analytics/expenses/category")
                .param("userId", "2")
                .param("month", "2")
                .param("year", "2020"))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        
        String resultString = result.andReturn().getResponse().getContentAsString();
        assertEquals(mapper.writeValueAsString(expectedList), resultString);
        verify(analyticsService).getAnalyticsByCategories(Mockito.anyInt(), Mockito.any(DatePart.class));
    }
    
    @Test
    void whenGetPopularExpensesAnalytics_thenReturnStatus200() throws Exception {
        mapper = new ObjectMapper();
        List<ExpensesAnalyticsView> expectedList = getPopularExpensesAnalytics();
        given(analyticsService.getPopularExpensesAnalytics(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(DatePart.class),
                Mockito.anyInt())).willReturn(expectedList);
        ResultActions result = mvc.perform(get("/api/analytics/expenses/popular")
                .param("userId", "2")
                .param("categoryId", "2")
                .param("month", "2")
                .param("year", "2020")
                .param("limit", "5"))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        
        String resultString = result.andReturn().getResponse().getContentAsString();
        assertEquals(mapper.writeValueAsString(expectedList), resultString);
        verify(analyticsService).getPopularExpensesAnalytics(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(DatePart.class),
                Mockito.anyInt());
    }
    
    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(analyticsService);
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
