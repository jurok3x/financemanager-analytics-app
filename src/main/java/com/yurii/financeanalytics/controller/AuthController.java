package com.yurii.financeanalytics.controller;

import com.yurii.financeanalytics.entity.payload.AuthRequest;
import com.yurii.financeanalytics.entity.payload.AuthResponse;
import com.yurii.financeanalytics.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/analytics/auth")
@PropertySource(value = {"classpath:/messages/info.properties"})
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class AuthController {
    
    private final AuthService authService;
    
    @Value("login.info")
    private String loginInfo;
    
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
        log.info(loginInfo, request.getEmail());
        return ResponseEntity.ok().body(authService.login(request));
    }

}
