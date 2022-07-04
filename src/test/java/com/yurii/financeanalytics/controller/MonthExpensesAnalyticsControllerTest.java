package com.yurii.financeanalytics.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yurii.financeanalytics.entity.view.MonthExpensesAnalyticsView;
import com.yurii.financeanalytics.service.MonthExpensesAnalyticsService;

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

@WebMvcTest(value = MonthExpensesAnalyticsController.class, useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = MonthExpensesAnalyticsController.class)})
@AutoConfigureMockMvc(addFilters = false)
class MonthExpensesAnalyticsControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private MonthExpensesAnalyticsService analyticsService;
    
    private ObjectMapper mapper;
    
    @Test
    void whenGetMonthAnalytics_thenReturnStatus200() throws Exception {
        mapper = new ObjectMapper();
        List<MonthExpensesAnalyticsView> expectedList = getMonthAnalytics();
        given(analyticsService.getMonthAnalytics(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt()))
        .willReturn(expectedList);
        ResultActions result = mvc.perform(get("/api/analytics/expenses/month")
                    .param("userId", "2")
                    .param("categoryId", "2")
                    .param("year", "2020"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        
        String resultString = result.andReturn().getResponse().getContentAsString();
        assertEquals(mapper.writeValueAsString(expectedList), resultString);
        verify(analyticsService).getMonthAnalytics(2, 2, 2020);
    }
    
    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(analyticsService);
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
