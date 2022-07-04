package com.yurii.financeanalytics.controller;

import com.yurii.financeanalytics.service.AnalyticsUtilsService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

import java.util.List;

@RestController
@RequestMapping("/api/utils")
@AllArgsConstructor
public class AnalyticsUtilsController {
    
    private static final String INCORRECT_ID = "Id must be greater than or equal to 1";
    private AnalyticsUtilsService utilsService;
    
    @GetMapping("/active-years/{userId}")
    @PreAuthorize("#userId == authentication.principal.id") // #userId == authentication.principal.id hasAuthority('utils:read')
    public ResponseEntity<List<Integer>> getAllActiveYears(
            @PathVariable @Min(value = 1, message = INCORRECT_ID) Integer userId) {
        return ResponseEntity.ok().body(utilsService.getActiveYears(userId));
    }
    

}
