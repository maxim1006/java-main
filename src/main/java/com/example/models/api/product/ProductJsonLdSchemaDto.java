package com.example.models.api.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductJsonLdSchemaDto {
    @JsonProperty("@context")
    private String context;
    @JsonProperty("@type")
    private String type;
    private String name;
    private String category;
    private List<ProductJsonLdSchemaOfferDto> offers;
    private String image;
    private String description;
    private String url;
}
