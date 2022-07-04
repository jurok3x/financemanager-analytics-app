package com.yurii.financeanalytics.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yurii.financeanalytics.service.AnalyticsUtilsService;

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

@WebMvcTest(value = AnalyticsUtilsController.class, useDefaultFilters = false, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = AnalyticsUtilsController.class)})
@AutoConfigureMockMvc(addFilters = false)
class AnalyticsUtilsControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private AnalyticsUtilsService utilsService;
    
    private ObjectMapper mapper;

    @Test
    void whenGetActiveYears_thenReturnCorrectResult() throws Exception {
        mapper = new ObjectMapper();
        List<Integer> expectedList = Arrays.asList(2022, 2021);
        given(utilsService.getActiveYears(Mockito.anyInt())).willReturn(expectedList);
        ResultActions result = mvc.perform(get("/api/utils/active-years/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        
        String resultString = result.andReturn().getResponse().getContentAsString();
        assertEquals(mapper.writeValueAsString(expectedList), resultString);
        verify(utilsService).getActiveYears(2);
    }
    
    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(utilsService);
    }

}
