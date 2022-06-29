package com.yurii.financeanalytics.service.impl;

import com.yurii.financeanalytics.dao.AnalyticsUtilsDao;
import com.yurii.financeanalytics.service.AnalyticsUtilsService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AnalyticsUtilsServiceImpl implements AnalyticsUtilsService {
    
    private AnalyticsUtilsDao utilsDao;

    @Override
    public List<Integer> getActiveYears(Integer userId) {
        return utilsDao.getAllActiveYears(userId);
    }

}
