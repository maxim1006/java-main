package com.example.models.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceInfo {
    private String id;
    private DiscountInfo discountInfo;
    private BigDecimal value;
}