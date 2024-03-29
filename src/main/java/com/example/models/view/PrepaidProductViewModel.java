package com.example.models.view;

import com.example.models.api.product.ProductType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PrepaidProductViewModel extends ProductViewModel {
    public PrepaidProductViewModel() {
        setType(ProductType.PREPAID);
    }
}
