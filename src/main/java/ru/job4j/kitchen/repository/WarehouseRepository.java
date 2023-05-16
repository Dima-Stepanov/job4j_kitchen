package ru.job4j.kitchen.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.kitchen.domain.Warehouse;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j KITCHEN
 * 5. Повествование основанное на хореографии [#458498]
 * WarehouseRepository хранилище обобщенного состояния склада,
 * продуктов для приготовления блюд Dish
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 16.05.2023
 */
public interface WarehouseRepository extends CrudRepository<Warehouse, Integer> {
    /**
     * Метод возвращает доступный набор ингридиентов для блюда
     *
     * @param dishId DISH ID
     * @return Warehouse
     */
    @Transactional
    @Query("SELECT w FROM Warehouse AS w WHERE w.dishId =:dishId AND w.dishNum > 0")
    Optional<Warehouse> getWarehouseByDishId(@Param("dishId") int dishId);
}
