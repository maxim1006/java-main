package com.example.models.view;

import com.example.models.api.product.ProductType;
import lombok.Data;

// важно чтобы у наследников в случае использования mapstruct были теже аннотации иначе будет ошибка
@Data
public class ProductViewModel {
    private String name;
    private ProductType type;
}
