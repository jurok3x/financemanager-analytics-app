package com.financemanager.demo.site.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemsAnalyticsView {
    
    private String name;
    private Integer count;
    private Double sum;

}
