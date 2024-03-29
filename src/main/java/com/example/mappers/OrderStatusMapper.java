package com.example.mappers;

import com.example.models.api.OrderStatusDto;
import com.example.models.internal.OrderStatus;
import com.example.models.internal.OrderStatusTypes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Objects;

@Mapper
public interface OrderStatusMapper {
    // все кроме Completed это OrderStatusTypes.PROCESSING
    @Mapping(source = "status", target = "status", qualifiedByName = "checkOrderStatus")
    OrderStatusDto from(OrderStatus orderStatus);

    // тут был затык что передается именно тип свойства к которому применен qualifiedByName
    @Named("checkOrderStatus")
    default OrderStatusTypes checkOrderStatus(OrderStatusTypes orderStatusTypes) {
        if (Objects.equals(orderStatusTypes.getValue(), OrderStatusTypes.COMPLETED.getValue())) {
            return OrderStatusTypes.COMPLETED;
        }

        return OrderStatusTypes.PROCESSING;
    }
}
