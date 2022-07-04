package com.yurii.financeanalytics.controller;

import com.yurii.financeanalytics.entity.view.MonthExpensesAnalyticsView;
import com.yurii.financeanalytics.service.MonthExpensesAnalyticsService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

import java.util.List;

@RestController
@RequestMapping("api/analytics/expenses/month")
@AllArgsConstructor
public class MonthExpensesAnalyticsController {
    
    private static final String INCORRECT_ID = "Id must be greater than or equal to 1";
    private MonthExpensesAnalyticsService analyticsService;
    
    @GetMapping
    @PreAuthorize("#userId == authentication.principal.id && hasAuthority('analytics:read')")
    public ResponseEntity<List<MonthExpensesAnalyticsView>> getMonthAnalytics(
            @RequestParam @Min(value = 1, message = INCORRECT_ID) Integer userId,
            @RequestParam(required = false) @Min(value = 1, message = INCORRECT_ID) Integer categoryId,
            @RequestParam(required = false) Integer year) {
        return ResponseEntity.ok().body(analyticsService.getMonthAnalytics(userId, categoryId, year));
    }

}
