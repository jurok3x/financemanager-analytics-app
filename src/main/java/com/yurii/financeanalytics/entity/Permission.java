package com.yurii.financeanalytics.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Permission {
    
    USER_READ("user:read"),
    UTILS_READ("utils:read"),
    ANALYTICS_READ("analytics:read");
    
    private final String permission;

}
