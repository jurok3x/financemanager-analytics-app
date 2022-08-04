package com.yurii.financeanalytics.dao.extractor;

import com.yurii.financeanalytics.entity.Role;
import com.yurii.financeanalytics.entity.User;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setRole(Role.builder()
                .id(rs.getInt("role_id"))
                .name(rs.getString("role_name"))
                .permissions(Stream.of((Object[])rs.getArray("permissions").getArray())
                        .map(permission -> permission.toString()).collect(Collectors.toSet()))
                .build());
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        return user;
    }

}
