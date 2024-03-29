package com.example.models.api;

import com.example.models.internal.OrderStatusTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusDto {
    OrderStatusTypes status;
}
