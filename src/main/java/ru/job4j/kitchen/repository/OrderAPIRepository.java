package ru.job4j.kitchen.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j KITCHEN
 * 5. Повествование основанное на хореографии [#458498]
 * OrderAPIRepository обращение в сервис job4j_order через REST API
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 16.05.2023
 */
@Repository
@Slf4j
public class OrderAPIRepository implements OrderRepository {
    private final RestTemplate restClint;
    @Value("${url-api.order}")
    private String urlApiOrder;

    public OrderAPIRepository(RestTemplate restClint) {
        this.restClint = restClint;
    }

    /**
     * Метод через REST обновляет статус модели Order в сервисе job4j_order
     *
     * @param orderId  Order ID
     * @param statusId Status ID
     * @return boolean true/false
     */
    @Override
    public boolean updateStatusByOrderId(int orderId, int statusId) {
        String url = String.format("%s/%d/status", urlApiOrder, orderId);
        return restClint.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(statusId),
                Void.class
        ).getStatusCode() != HttpStatus.NOT_FOUND;
    }
}
