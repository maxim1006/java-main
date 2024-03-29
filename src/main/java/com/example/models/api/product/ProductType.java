package com.example.models.api.product;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ProductType {
    PREPAID("Prepaid"),
    POSTPAID("Postpaid"),
    DEFAULT("Default");

    @Getter
    @JsonValue
    private final String value;

    @Override
    public String toString() {
        return value;
    }
}