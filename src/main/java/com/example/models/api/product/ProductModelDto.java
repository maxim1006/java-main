package com.example.models.api.product;

import com.example.models.api.PageSeoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
public abstract class ProductModelDto {
    private String id;
    private String offerSlug;
    private ProductType type;
    private String name;

    @JsonIgnore
    private PageSeoDto pageSeoViewModel;
}
