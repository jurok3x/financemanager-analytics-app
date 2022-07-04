package com.yurii.financeanalytics.entity.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthExpensesAnalyticsView {
    
    private Integer monthId;
    private String monthName;
    private Double sum;

}
