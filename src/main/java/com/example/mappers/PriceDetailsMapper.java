package com.example.mappers;

import com.example.models.api.PriceDetailsDto;
import com.example.models.internal.DiscountInfo;
import com.example.models.internal.PriceWithTax;
import com.example.utils.NumericIdGenerator;
import jakarta.inject.Inject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

// пример multiple sources собираю DTO из нескольких источников DiscountInfo и PriceWithTax
@Mapper
public abstract class PriceDetailsMapper {
    @Inject
    NumericIdGenerator numericIdGenerator;

    @Mapping(target = "id", expression = "java(String.valueOf(numericIdGenerator.getNext()))")
    @Mapping(source = "priceWithTax.tax", target = "priceTax")
    @Mapping(source = "priceWithTax.value", target = "priceValue")
    @Mapping(source = "discountInfo.renamed", target = "discountRenamed")
    public abstract PriceDetailsDto from(PriceWithTax priceWithTax, DiscountInfo discountInfo);
}
