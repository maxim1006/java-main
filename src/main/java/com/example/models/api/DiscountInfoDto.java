package com.example.models.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscountInfoDto {
    private String id;
    private String name;
    private String renamedDto;
    private LocalDate date;
}