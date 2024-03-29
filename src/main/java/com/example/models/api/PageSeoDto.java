package com.example.models.api;

import com.example.models.api.product.ProductJsonLdSchemaDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageSeoDto {
    private String robots;
    private String title;
    private String description;
    private ProductJsonLdSchemaDto jsonLdSchema;
}
