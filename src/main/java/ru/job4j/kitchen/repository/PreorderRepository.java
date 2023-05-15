package ru.job4j.kitchen.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.kitchen.domain.Preorder;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j KITCHEN
 * 5. Повествование основанное на хореографии [#458498]
 * PreorderRepository хранилище моделей данных Preorder
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 14.05.2023
 */
public interface PreorderRepository extends CrudRepository<Preorder, Integer> {
}
