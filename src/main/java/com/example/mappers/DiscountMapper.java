package com.example.mappers;

import com.example.models.internal.DiscountInfo;
import com.example.models.api.DiscountInfoDto;
import com.example.models.internal.DiscountInfoWithLongId;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

// пример ренейма переменном в таргет классе
// Пример смены типа, DiscountInfoWithLongId имеет long id а в DiscountInfoDto String Id
// imports = {LocalDate.class} нужен для defaultExpression = "java(LocalDate.now())"
@Mapper(imports = {LocalDate.class})
public interface DiscountMapper {
    @BeforeMapping
    default void beforeMap(DiscountInfo discountInfo) {
        if (Objects.nonNull(discountInfo) && StringUtils.isEmpty(discountInfo.getName())) {
            discountInfo.setName("Max");
        }
    }

    @AfterMapping
    default void afterMap(DiscountInfo discountInfo) {
        if (StringUtils.isEmpty(discountInfo.getId())) {
            discountInfo.setId(UUID.randomUUID().toString());
        }
    }

    // если в сорсе и таргете называются по-разному - надо указать
    @Mapping(source = "renamed", target = "renamedDto")
    // мапстракт сделает, где ISO date yyyy-mm-dd
    // dateTimeFormatter.ISO_LOCAL_DATE.format( discountInfo.getDate() )
    // "yyyy-mm-dd" будет по-умолчанию но для примера если надо поменять (например yyyy-MMM-dd)
    @Mapping(source = "date", target = "date", dateFormat = "dd/MM/yyyy", defaultExpression = "java(LocalDate.now())")
    DiscountInfoDto from(DiscountInfo discountInfo);

    // long в String mapstruct перевел автоматом
    DiscountInfoDto from(DiscountInfoWithLongId discountInfo);

    // String to long mapstruct перевел Long.parseLong()
     DiscountInfoWithLongId fromDto(DiscountInfoDto discountInfoDto);
}
