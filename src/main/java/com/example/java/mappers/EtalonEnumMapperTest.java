package com.example.java.mappers;

import com.example.java.enums.EnumEtalon;
import com.example.mappers.EtalonEnumMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class EtalonEnumMapperTest {
    @Inject
    EtalonEnumMapper etalonEnumMapper;

    public void init() {
        System.out.println(etalonEnumMapper.from(EnumEtalon.ALL));
    }
}
