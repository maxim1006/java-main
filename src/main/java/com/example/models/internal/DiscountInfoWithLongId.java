package com.example.models.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscountInfoWithLongId {
    private long id;
    private String name;
    private String renamed;
}