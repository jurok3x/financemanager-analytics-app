package com.yurii.financeanalytics.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import com.yurii.financeanalytics.dao.AnalyticsUtilsDao;
import com.yurii.financeanalytics.service.impl.AnalyticsUtilsServiceImpl;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(value = { MockitoExtension.class })
class AnalyticsUtilsServiceImplTest {
    
    @Mock
    private AnalyticsUtilsDao utilsDao;
    
    @InjectMocks
    private AnalyticsUtilsServiceImpl utilsService;

    @Test
    void whenUserIdIsProvided_thenReturnCorrectList() {
        List<Integer> expectedList = Arrays.asList(2022, 2021);
        given(utilsService.getActiveYears(Mockito.anyInt())).willReturn(expectedList);
        assertEquals(expectedList, utilsService.getActiveYears(1));
        verify(utilsDao).getAllActiveYears(1);
    }
    
    void tearDown() {
        verifyNoInteractions(utilsDao);
    }

}
