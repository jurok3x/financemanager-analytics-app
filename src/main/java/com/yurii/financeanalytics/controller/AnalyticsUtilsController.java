package com.yurii.financeanalytics.controller;

import com.yurii.financeanalytics.service.AnalyticsUtilsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/utils")
@RequiredArgsConstructor
@Slf4j
@PropertySource(value = {"classpath:/messages/info.properties"})
@SecurityRequirement(name = "bearerAuth")
public class AnalyticsUtilsController {
    
    private final AnalyticsUtilsService utilsService;
    
    @Value("active_years.info")
    private String activeYearsInfo;
    
    @GetMapping("/active-years/{userId}")
    @PreAuthorize("#userId == authentication.principal.id && hasAuthority('utils:read')") 
    public ResponseEntity<List<Integer>> getAllActiveYears(
            @PathVariable Integer userId) {
        log.info(activeYearsInfo, userId);
        return ResponseEntity.ok().body(utilsService.getActiveYears(userId));
    }
    
}