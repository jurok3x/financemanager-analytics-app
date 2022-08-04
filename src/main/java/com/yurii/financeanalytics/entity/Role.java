package com.yurii.financeanalytics.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
public class Role {
    
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String name;
    private Set<String> permissions;
    
    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> userPermissions = null;
        if(permissions == null || permissions.isEmpty()) {
            return userPermissions;
        }
        userPermissions = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission)).collect(Collectors.toSet());
        userPermissions.add(new SimpleGrantedAuthority(this.name));
        return userPermissions;
    }

}
