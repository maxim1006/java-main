package com.example.mappers;

import com.example.models.api.PriceWithTaxDto;
import com.example.models.internal.PriceInfo;
import com.example.models.api.PriceInfoDto;
import com.example.models.internal.PriceWithTax;
import com.example.utils.NumericIdGenerator;
import jakarta.inject.Inject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

// пример маппинга внутреннего nested bean
// могу использовать доб мапперы, обязательно при этом надо добавить
// @Mapping(target = "discountInfoDto", source = "discountInfo")
// чтобы сработал маппинг именно DiscountMapper
// если убрать (uses = { DiscountMapper.class }) то mapstruct создаст свой доп маппер (кастомный)
@Mapper(uses = { DiscountMapper.class })
public abstract class PriceMapper {
    @Inject
    NumericIdGenerator numericIdGenerator;

    @Mapping(target = "id", expression = "java(String.valueOf(numericIdGenerator.getNext()))")
    @Mapping(target = "discountInfoDto", source = "discountInfo")
    public abstract PriceInfoDto from(PriceInfo priceInfo);

    // пример кастомного маппинга
    public PriceWithTaxDto from(PriceWithTax priceWithTax) {
        return PriceWithTaxDto.builder()
                .value(BigDecimal.valueOf(100))
                .tax(BigDecimal.ZERO)
                .build();
    }
}
