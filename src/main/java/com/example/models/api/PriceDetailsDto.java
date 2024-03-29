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
public class PriceDetailsDto {
    private String id;
    private BigDecimal priceValue;
    private BigDecimal priceTax;
    private String discountRenamed;
}
