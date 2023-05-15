package ru.job4j.kitchen.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.kitchen.domain.dto.OrderDTO;
import ru.job4j.kitchen.mapper.PreorderMapper;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j KITCHEN
 * 5. Повествование основанное на хореографии [#458498]
 * KafkaKitchenByOrderService Слой сервиса отправки приема модели OrderDTO через KafkaTemplate
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 04.05.2023
 */
@Service
@AllArgsConstructor
@Slf4j
public class KafkaKitchenByOrderService implements KafkaService<String, OrderDTO, OrderDTO> {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final SimplePreorderService preorderService;
    private final PreorderMapper preorderMapper;
    private static final String TOPIC_PREORDERS = "job4j_preorder";
    private static final String TOPIC_COOKED = "job4j_cooked_order";

    @Override
    public void sendMessage(String topic, String key, OrderDTO type) {
        var future = kafkaTemplate.send(topic, key, type);
        future.addCallback(o -> log.debug("SUCCESS message, key: {}, type: {}", key, type),
                o -> log.error("FAILURE message, key: {}, type: {}", key, type));
        kafkaTemplate.flush();
    }

    @KafkaListener(topics = TOPIC_PREORDERS)
    @Override
    public OrderDTO receive(ConsumerRecord<String, OrderDTO> record) {
        log.debug("Partition: {}", record.partition());
        log.debug("Key: {}", record.key());
        log.debug("Value: {}", record.value());
        var preorder = preorderMapper.getPreorderByOrderDTO(record.value());
        preorderService.save(preorder);
        return record.value();
    }
}
