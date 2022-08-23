package com.yurii.financeanalytics.controller;

import com.yurii.financeanalytics.entity.view.MonthExpensesAnalyticsView;
import com.yurii.financeanalytics.service.MonthExpensesAnalyticsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("api/analytics/expenses/month")
@RequiredArgsConstructor
@Slf4j
@PropertySource(value = {"classpath:/messages/info.properties"})
@SecurityRequirement(name = "bearerAuth")
public class MonthExpensesAnalyticsController {
    
    private final MonthExpensesAnalyticsService analyticsService;
    
    @Value("month_analytics.info")
    private String monthAnalyticsInfo;
    
    @GetMapping("/user/{userId}")
    @PreAuthorize("#userId == authentication.principal.id && hasAuthority('analytics:read')")
    public ResponseEntity<List<MonthExpensesAnalyticsView>> getMonthAnalytics(
            @PathVariable Integer userId,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Integer year) {
        log.info(monthAnalyticsInfo, userId, categoryId, year);
        return ResponseEntity.ok().body(analyticsService.getMonthAnalytics(userId, categoryId, year));
    }

}
