package com.example.models.api;

import com.example.models.api.DiscountInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceInfoDto {
    private String id;
    private DiscountInfoDto discountInfoDto;
}