package ru.job4j.kitchen.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.kitchen.domain.Status;
import ru.job4j.kitchen.domain.dto.OrderDTO;
import ru.job4j.kitchen.mapper.PreorderMapper;
import ru.job4j.kitchen.repository.OrderRepository;

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
    private final OrderRepository orderRepository;
    private final PreorderMapper preorderMapper;
    private final CookedService cookedService;

    private final Status cancelled = new Status(2, "Отменен");
    private final Status ready = new Status(3, "Готов к выдаче");

    private static final String TOPIC_PREORDERS = "job4j_preorder";

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
        var orderDTO = record.value();

        sendKitchen(orderDTO);

        return orderDTO;
    }

    private void sendKitchen(OrderDTO orderDTO) {
        var preorder = preorderMapper.getPreorderByOrderDTO(orderDTO);
        var cooked = cookedService.dishCooked(preorder.getDishId());
        if (cooked) {
            preorder.setStatus(ready);
        }
        if (!cooked) {
            preorder.setStatus(cancelled);
        }
        preorderService.save(preorder);
        orderRepository.updateStatusByOrderId(preorder.getOrderId(), preorder.getStatus().getId());
    }
}
