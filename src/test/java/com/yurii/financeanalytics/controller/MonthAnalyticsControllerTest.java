package com.yurii.financeanalytics.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yurii.financeanalytics.entity.view.MonthAnalyticsView;
import com.yurii.financeanalytics.service.MonthAnalyticsService;

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

@WebMvcTest(value = MonthAnalyticsController.class, useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = MonthAnalyticsController.class)})
@AutoConfigureMockMvc(addFilters = false)
class MonthAnalyticsControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private MonthAnalyticsService analyticsService;
    
    private ObjectMapper mapper;
    
    @Test
    void whenGetMonthExpensesAnalytics_thenReturnStatus200() throws Exception {
        mapper = new ObjectMapper();
        List<MonthAnalyticsView> expectedList = getMonthAnalytics();
        given(analyticsService.getMonthExpensesAnalytics(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt()))
        .willReturn(expectedList);
        ResultActions result = mvc.perform(get("/api/analytics/month/expenses/user/2")
                    .param("categoryId", "2")
                    .param("year", "2020"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        
        String resultString = result.andReturn().getResponse().getContentAsString();
        assertEquals(mapper.writeValueAsString(expectedList), resultString);
        verify(analyticsService).getMonthExpensesAnalytics(2, 2, 2020);
    }
    
    void whenGetMonthIncomesAnalytics_thenReturnStatus200() throws Exception {
        mapper = new ObjectMapper();
        List<MonthAnalyticsView> expectedList = getMonthAnalytics();
        given(analyticsService.getMonthIncomesAnalytics(Mockito.anyInt(), Mockito.anyInt()))
        .willReturn(expectedList);
        ResultActions result = mvc.perform(get("/api/analytics/month/incomes/user/2")
                    .param("year", "2020"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        
        String resultString = result.andReturn().getResponse().getContentAsString();
        assertEquals(mapper.writeValueAsString(expectedList), resultString);
        verify(analyticsService).getMonthIncomesAnalytics(2, 2020);
    }
    
    void whenGetMonthBalanceAnalytics_thenReturnStatus200() throws Exception {
        mapper = new ObjectMapper();
        List<MonthAnalyticsView> expectedList = getMonthAnalytics();
        given(analyticsService.getMonthBalanceAnalytics(Mockito.anyInt(), Mockito.anyInt()))
        .willReturn(expectedList);
        ResultActions result = mvc.perform(get("/api/analytics/month/balance/user/2")
                    .param("year", "2020"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        
        String resultString = result.andReturn().getResponse().getContentAsString();
        assertEquals(mapper.writeValueAsString(expectedList), resultString);
        verify(analyticsService).getMonthBalanceAnalytics(2, 2020);
    }
    
    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(analyticsService);
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
