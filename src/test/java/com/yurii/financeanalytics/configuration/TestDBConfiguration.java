package com.yurii.financeanalytics.configuration;

import com.yurii.financeanalytics.dao.extractor.ExpensesAnalyticsViewMapper;
import com.yurii.financeanalytics.dao.extractor.MonthExpensesAnalyticsViewMapper;
import com.yurii.financeanalytics.dao.extractor.UserRowMapper;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@TestConfiguration
@Import(value = { UserRowMapper.class, ExpensesAnalyticsViewMapper.class, MonthExpensesAnalyticsViewMapper.class })
public class TestDBConfiguration {
    
    @Bean
    public DataSource getDataSource() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:/db/user/schema.sql")
                .addScript("classpath:/db/user/test-data.sql")
                .addScript("classpath:/db/category/schema.sql")
                .addScript("classpath:/db/category/test-data.sql")
                .addScript("classpath:/db/expenses/schema.sql")
                .addScript("classpath:/db/expenses/test-data.sql")
                .addScript("classpath:/db/expenses/months-schema.sql")
                .addScript("classpath:/db/expenses/months-data.sql")
                .build();
        return dataSource;
    }
    
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(getDataSource());
    }

}
