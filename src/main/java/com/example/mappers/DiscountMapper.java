package com.example.mappers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;

@Mapper
public interface DiscountMapper {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class DiscountInfoDto {
        private String id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class DiscountInfo {
        private String id;
        private String name;
    }

    DiscountInfoDto from(DiscountInfo discountInfo);
}
