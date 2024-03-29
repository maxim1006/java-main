package com.example.models.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscountInfo {
    private String id;
    private String name;
    private String renamed;
    private LocalDate date;
}