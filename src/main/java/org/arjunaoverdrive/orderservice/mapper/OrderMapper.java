package org.arjunaoverdrive.orderservice.mapper;

import org.arjunaoverdrive.kafka.OrderMessage;
import org.arjunaoverdrive.orderservice.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMessage toOrderMessage(Order request);
}
