package org.arjunaoverdrive.orderservice.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arjunaoverdrive.kafka.OrderMessage;
import org.arjunaoverdrive.orderservice.service.OrderMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderMessageServiceImpl implements OrderMessageService {

    private final KafkaTemplate<String, OrderMessage> kafkaTemplate;
    @Value("${app.kafka.kafkaTopicName}")
    private String topicName;
    @Override
    public void submitOrderMessage(OrderMessage message) {
        CompletableFuture<SendResult<String, OrderMessage>> future =
                kafkaTemplate.send(topicName, UUID.randomUUID().toString(), message);
        future.whenComplete((res, ex) -> {
            if(ex == null){
                log.info("Sent message: {} with offset :{}", message, res.getRecordMetadata().offset());
            }else {
                log.warn("Unable to send message: {} due to {}", message, ex.getMessage());
            }
        });
    }
}
