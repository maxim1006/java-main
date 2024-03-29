package com.example.models.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceInfoWithDiscountDto {
    private String id;
    // это свойство замапится из DiscountInfo
    private String discountName;
    private BigDecimal value;
    private String type;
}