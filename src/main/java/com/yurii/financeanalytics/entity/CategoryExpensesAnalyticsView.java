package com.yurii.financeanalytics.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryExpensesAnalyticsView {

    private String name;
    private Integer count;
    private Double sum;
}
