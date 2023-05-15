package ru.job4j.kitchen.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.kitchen.domain.Status;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j KITCHEN
 * 5. Повествование основанное на хореографии [#458498]
 * StatusRepository хранилище статусов заказа.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 14.05.2023
 */
public interface StatusRepository extends CrudRepository<Status, Integer> {
}
