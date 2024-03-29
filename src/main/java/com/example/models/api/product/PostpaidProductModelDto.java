package com.example.models.api.product;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostpaidProductModelDto extends ProductModelDto {
    public PostpaidProductModelDto() {
        setType(ProductType.POSTPAID);
    }
}
