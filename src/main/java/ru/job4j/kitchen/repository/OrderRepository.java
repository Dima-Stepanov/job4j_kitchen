package ru.job4j.kitchen.repository;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j KITCHEN
 * 5. Повествование основанное на хореографии [#458498]
 * OrderRepository интерфейс описывает поведение хрaнилища сервиса job4j_order
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 16.05.2023
 */
public interface OrderRepository {
    boolean updateStatusByOrderId(int orderId, int statusId);
}
