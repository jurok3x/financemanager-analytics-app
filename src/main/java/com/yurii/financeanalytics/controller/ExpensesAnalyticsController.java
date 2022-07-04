package com.yurii.financeanalytics.controller;

import com.yurii.financeanalytics.entity.view.CategoryExpensesAnalyticsView;
import com.yurii.financeanalytics.entity.view.ExpensesAnalyticsView;
import com.yurii.financeanalytics.service.ExpensesAnalyticsService;

import lombok.AllArgsConstructor;

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
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ExpensesAnalyticsController {
    
    private static final String INCORRECT_ID = "Id must be greater than or equal to 1";
    private static final String INCORRECT_MONTH = "Incorrect month";
    private static final String INCORRECT_LIMIT = "Incorrect limit";
    private static final String INCORRECT_OFFSET = "Incorrect offset";
    private ExpensesAnalyticsService analyticsService;
    
    @GetMapping("/category")
    @PreAuthorize("#userId == authentication.principal.id && hasAuthority('analytics:read')")
    public ResponseEntity<List<CategoryExpensesAnalyticsView>> getCategoryAnalytics(
            @RequestParam @Min(value = 1, message = INCORRECT_ID) Integer userId,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) @Min(value = 1, message = INCORRECT_MONTH)
                @Max(value = 12, message = INCORRECT_MONTH) Integer month) {
                return ResponseEntity.ok().body(analyticsService.getAnalyticsByCategories(userId, month, year));
    }
    
    @GetMapping("/popular")
    @PreAuthorize("#userId == authentication.principal.id && hasAuthority('analytics:read')")
    public ResponseEntity<List<ExpensesAnalyticsView>> getMostPopularExpenses(
            @RequestParam @Min(value = 1, message = INCORRECT_ID) Integer userId,
            @RequestParam(required = false) @Min(value = 1, message = INCORRECT_ID) Integer categoryId,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) @Min(value = 1, message = INCORRECT_MONTH) @Max(value = 12, message = INCORRECT_MONTH) Integer month,
            @RequestParam(required = false) @Min(value = 1, message = INCORRECT_OFFSET) Integer offset,
            @RequestParam(required = false) @Min(value = 0, message = INCORRECT_LIMIT) Integer limit) {
                return ResponseEntity.ok().body(analyticsService.getPopularExpensesAnalytics(userId, categoryId, month, year, offset, limit));
    }

}
