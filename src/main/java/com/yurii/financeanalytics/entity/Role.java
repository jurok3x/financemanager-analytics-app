package com.yurii.financeanalytics.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Role {
    
    USER(Arrays.asList(Permission.USER_READ, Permission.UTILS_READ).stream().collect(Collectors.toSet())),
    ADMIN(Arrays.asList(Permission.USER_READ, Permission.UTILS_READ).stream().collect(Collectors.toSet()));
    
    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> userPermissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
        userPermissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return userPermissions;
    }
    
    private final Set<Permission> permissions;

}
