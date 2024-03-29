package com.example.models.internal;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum OrderStatusTypes {
    ENTERING("Entering"),
    PROCESSING("Processing"),
    PENDING_PAYMENT("Pending payment"),
    ORDER_UPDATED("Order updated"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled"),
    UNKNOWN("Unknown");

    @Getter
    @JsonValue
    private final String value;

    @Override
    public String toString() {
        return value;
    }
}
