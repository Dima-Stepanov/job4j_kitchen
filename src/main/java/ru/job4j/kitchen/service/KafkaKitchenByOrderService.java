package ru.job4j.kitchen.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.job4j.kitchen.domain.dto.OrderDTO;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j KITCHEN
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 04.05.2023
 */
@Service
@AllArgsConstructor
@Slf4j
public class KafkaKitchenByOrderService implements KafkaService<String, OrderDTO, OrderDTO> {
    private static final String TOPIC_ORDERS = "job4j_preorder";

    @Override
    public void sendMessage(String topic, String key, OrderDTO type) {
        log.error("The method 'sendMessage', is not overridden");
        throw new RuntimeException("The method 'sendMessage', is not overridden");
    }

    @KafkaListener(topics = TOPIC_ORDERS)
    @Override
    public OrderDTO receive(ConsumerRecord<String, OrderDTO> record) {
        log.debug("Partition: {}", record.partition());
        log.debug("Key: {}", record.key());
        log.debug("Value: {}", record.value());
        return record.value();
    }
}
