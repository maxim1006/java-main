package com.example.java.mappers;

import com.example.java.enums.EnumEtalon;
import com.example.mappers.DiscountMapper;
import com.example.mappers.EtalonEnumMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class MapperTest {
    @Inject
    EtalonEnumMapper etalonEnumMapper;

    @Inject
    DiscountMapper discountMapper;

    public void init() {
        System.out.println(etalonEnumMapper.from(EnumEtalon.ALL)); // all values
        System.out.println(discountMapper.from(new DiscountMapper.DiscountInfo()));
    }
}
