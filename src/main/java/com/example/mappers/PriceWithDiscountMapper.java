package com.example.mappers;

import com.example.models.api.PriceInfoDto;
import com.example.models.api.PriceInfoWithDiscountDto;
import com.example.models.api.PriceWithTaxDto;
import com.example.models.internal.PriceInfo;
import com.example.models.internal.PriceWithTax;
import com.example.utils.NumericIdGenerator;
import jakarta.inject.Inject;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

// Пример со свойством из nested bean (внутреннего объекта) которое мапится на свойство DTO
@Mapper
public interface PriceWithDiscountMapper {

    @Mapping(source = "discountInfo.name", target = "discountName")
    @Mapping(source = "id", target = "id", ignore = true) // не будет проставляться id из source
    @Mapping(source = "value", target = "value", defaultValue = "0") // установка дефолтного значения
    @Mapping(target = "type", constant = "New") // хоть и в сорсе нет type но в таргет установится New
    public PriceInfoWithDiscountDto from(PriceInfo priceInfo);

    @InheritInverseConfiguration
    public PriceInfo from(PriceInfoWithDiscountDto priceInfoWithDiscountDto);
}
