package com.yurii.financeanalytics.entity.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String login;
    private String password;
}
