package com.example.models.api.product;

import com.example.models.api.PageSeoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class PrepaidProductModelDto extends ProductModelDto {
    public PrepaidProductModelDto() {
        setType(ProductType.PREPAID);
    }
}
