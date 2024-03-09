package org.arjunaoverdrive.orderservice.service;

import org.arjunaoverdrive.kafka.OrderMessage;

public interface OrderMessageService {
    void submitOrderMessage(OrderMessage message);
}
