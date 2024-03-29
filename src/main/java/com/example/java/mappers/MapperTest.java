package com.example.java.mappers;

import com.example.java.enums.EnumEtalon;
import com.example.mappers.*;
import com.example.models.api.product.PrepaidProductModelDto;
import com.example.models.internal.DiscountInfo;
import com.example.models.internal.OrderStatus;
import com.example.models.internal.OrderStatusTypes;
import com.example.models.internal.PriceInfo;
import com.example.utils.JsonMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;


@ApplicationScoped
public class MapperTest {
    @Inject
    EtalonEnumMapper etalonEnumMapper;

    @Inject
    DiscountMapper discountMapper;

    @Inject
    PriceMapper priceMapper;

    @Inject
    PriceWithDiscountMapper priceWithDiscountMapper;

    @Inject
    ProductViewModelMapper productViewModelMapper;

    @Inject
    OrderStatusMapper orderStatusMapper;

    @Inject
    JsonMapper jsonMapper;

    public void init() {
        System.out.println(etalonEnumMapper.from(EnumEtalon.ALL)); // all values
        System.out.println(etalonEnumMapper.from(null)); // Default
        System.out.println(discountMapper.from(new DiscountInfo("1", "Max", "max", LocalDate.now()))); // или LocalDate.of(2024, 3, 29)
        System.out.println(priceMapper.from(new PriceInfo()));
        System.out.println(priceWithDiscountMapper.from(new PriceInfo()));

        PrepaidProductModelDto prepaidProductModelDto = new PrepaidProductModelDto();
        prepaidProductModelDto.setName("Prepaid Name");
        System.out.println(jsonMapper.convertObjectToString(productViewModelMapper.from(prepaidProductModelDto)));
        System.out.println(orderStatusMapper.from(OrderStatus.builder().status(OrderStatusTypes.ORDER_UPDATED).build())); // Uncompleted
    }
}
