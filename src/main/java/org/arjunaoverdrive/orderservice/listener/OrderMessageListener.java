package org.arjunaoverdrive.orderservice.listener;

import lombok.extern.slf4j.Slf4j;
import org.arjunaoverdrive.kafka.OrderMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class OrderMessageListener {

    @KafkaListener(topics = "${app.kafka.kafkaTopicName}",
            groupId = "${app.kafka.kafkaMessageGroupId}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(@Payload OrderMessage message,
                       @Header(value = KafkaHeaders.RECEIVED_KEY, required = false)UUID key,
                       @Header(KafkaHeaders.RECEIVED_TOPIC)String topic,
                       @Header(KafkaHeaders.RECEIVED_PARTITION)Integer partition,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP)Long timestamp){
        log.info("Received message: {}", message);
        log.info("Key: {}; Partition: {}; Topic: {}; Timestamp: {}", key, partition, topic, timestamp);
    }
}
