package com.example.models.view;

import com.example.models.api.product.ProductType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostpaidProductViewModel extends ProductViewModel {
    public PostpaidProductViewModel() {
        setType(ProductType.POSTPAID);
    }
}
