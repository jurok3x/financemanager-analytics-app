package com.financemanager.dao;

import static org.junit.jupiter.api.Assertions.*;

import com.financemanager.configuration.TestDBConfiguration;
import com.financemanager.dao.impl.AnalyticsUtilsDaoImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest(classes = {TestDBConfiguration.class, AnalyticsUtilsDaoImpl.class})
class AnalyticsUtilsDaoImplTest {
    
    @Autowired
    private AnalyticsUtilsDao utilsDao;

    @Test
    void returnCorrectActiveYearsList() {
        assertEquals(Arrays.asList(2022, 2021), utilsDao.getAllActiveYears(1));
    }

}
