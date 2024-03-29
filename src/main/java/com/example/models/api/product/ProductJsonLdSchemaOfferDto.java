package com.example.models.api.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductJsonLdSchemaOfferDto {
    @JsonProperty("@type")
    private String type;
    private String price;
    private String priceCurrency;
}
