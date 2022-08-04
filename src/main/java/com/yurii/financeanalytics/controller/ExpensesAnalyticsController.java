package com.yurii.financeanalytics.controller;

import com.yurii.financeanalytics.entity.payload.DatePart;
import com.yurii.financeanalytics.entity.view.CategoryExpensesAnalyticsView;
import com.yurii.financeanalytics.entity.view.ExpensesAnalyticsView;
import com.yurii.financeanalytics.service.ExpensesAnalyticsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("api/analytics/expenses")
@RequiredArgsConstructor
@Slf4j
@PropertySource(value = {"classpath:/messages/info.properties"})
@SecurityRequirement(name = "bearerAuth")
public class ExpensesAnalyticsController {
    
    private final ExpensesAnalyticsService analyticsService;
    
    @Value("category_analytics.info")
    private String categoryAnalyticsInfo;
    @Value("popular_analytics.info")
    private String popularAnalyticsInfo;
    
    @GetMapping("/category")
    @PreAuthorize("#userId == authentication.principal.id && hasAuthority('analytics:read')")
    public ResponseEntity<List<CategoryExpensesAnalyticsView>> getCategoryAnalytics(
            @RequestParam Integer userId,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) @Min(value = 1)
                @Max(value = 12) Integer month) {
        log.info(categoryAnalyticsInfo, userId, year, month);
        return ResponseEntity.ok().body(analyticsService.getAnalyticsByCategories(userId, new DatePart(month, year)));
    }
    
    @GetMapping("/popular")
    @PreAuthorize("#userId == authentication.principal.id && hasAuthority('analytics:read')")
    public ResponseEntity<List<ExpensesAnalyticsView>> getMostPopularExpenses(
            @RequestParam Integer userId,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) @Min(value = 1) @Max(value = 1) Integer month,
            @RequestParam(required = false) @Min(value = 0) Integer limit) {
        log.info(popularAnalyticsInfo, userId, categoryId, year, month);
        return ResponseEntity.ok().body(analyticsService.getPopularExpensesAnalytics(userId, categoryId, new DatePart(month, year), limit));
    }

}
