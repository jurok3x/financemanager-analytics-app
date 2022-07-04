package com.yurii.financeanalytics.controller;

import com.yurii.financeanalytics.entity.payload.AuthRequest;
import com.yurii.financeanalytics.entity.payload.AuthResponse;
import com.yurii.financeanalytics.service.AuthService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
@SecurityRequirement(name = "bearerAuth")
public class AuthController {
    
    private AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
        return ResponseEntity.ok().body(authService.login(request));
    }

}
