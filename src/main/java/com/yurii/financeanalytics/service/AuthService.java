package com.yurii.financeanalytics.service;

import com.yurii.financeanalytics.entity.payload.AuthRequest;
import com.yurii.financeanalytics.entity.payload.AuthResponse;

public interface AuthService {
    
    AuthResponse login(AuthRequest request);

}
