package com.yurii.financeanalytics.controller;

import com.yurii.financeanalytics.entity.view.MonthAnalyticsView;
import com.yurii.financeanalytics.service.MonthAnalyticsService;

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
@RequestMapping("api/analytics/month")
@RequiredArgsConstructor
@Slf4j
@PropertySource(value = {"classpath:/messages/info.properties"})
@SecurityRequirement(name = "bearerAuth")
public class MonthAnalyticsController {
    
    private final MonthAnalyticsService analyticsService;
    
    @Value("month_expenses_analytics.info")
    private String monthExpensesAnalyticsInfo;
    @Value("month_incomes_analytics.info")
    private String monthIncomesAnalyticsInfo;
    @Value("month_balance_analytics.info")
    private String monthBalanceAnalyticsInfo;
    
    @GetMapping("/expenses/user/{userId}")
    @PreAuthorize("#userId == authentication.principal.id && hasAuthority('analytics:read')")
    public ResponseEntity<List<MonthAnalyticsView>> getMonthExpensesAnalytics(
            @PathVariable Integer userId,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Integer year) {
        log.info(monthExpensesAnalyticsInfo, userId, categoryId, year);
        return ResponseEntity.ok().body(analyticsService.getMonthExpensesAnalytics(userId, categoryId, year));
    }
    
    @GetMapping("/incomes/user/{userId}")
    @PreAuthorize("#userId == authentication.principal.id && hasAuthority('analytics:read')")
    public ResponseEntity<List<MonthAnalyticsView>> getMonthIncomesAnalytics(
            @PathVariable Integer userId,
            @RequestParam(required = false) Integer year) {
        log.info(monthIncomesAnalyticsInfo, userId, year);
        return ResponseEntity.ok().body(analyticsService.getMonthIncomesAnalytics(userId,  year));
    }
    
    @GetMapping("/balance/user/{userId}")
    @PreAuthorize("#userId == authentication.principal.id && hasAuthority('analytics:read')")
    public ResponseEntity<List<MonthAnalyticsView>> getMonthBalanceAnalytics(
            @PathVariable Integer userId,
            @RequestParam(required = false) Integer year) {
        log.info(monthBalanceAnalyticsInfo, userId, year);
        return ResponseEntity.ok().body(analyticsService.getMonthBalanceAnalytics(userId,  year));
    }

}
