package com.financemanager.demo.site.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthAnalyticsView {
    
    private Integer monthId;
    private String monthName;
    private Double sum;

}
