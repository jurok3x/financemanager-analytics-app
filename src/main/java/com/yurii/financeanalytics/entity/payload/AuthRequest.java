package com.yurii.financeanalytics.entity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequest {
    private String token;
    private String type;
}
