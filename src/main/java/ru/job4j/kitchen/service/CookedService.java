package ru.job4j.kitchen.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.job4j.kitchen.repository.WarehouseRepository;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j KITCHEN
 * 5. Повествование основанное на хореографии [#458498]
 * CookedService класс имитирует процесс приготовления блюда.
 * При этом ингредиенты получаются со склада и уменьшаются на 1
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 16.05.2023
 */
@Service
@AllArgsConstructor
@Slf4j
public class CookedService {
    private WarehouseRepository warehousesRepository;

    /**
     * Метод имитирует приготовления блюда
     *
     * @param dishId Dish ID
     * @return boolean true/false
     */
    public boolean dishCooked(int dishId) {
        var warehouse = warehousesRepository.getWarehouseByDishId(dishId);
        boolean result = false;
        if (warehouse.isPresent()) {
            warehouse.get().setDishNum(warehouse.get().getDishNum() - 1);
            warehousesRepository.save(warehouse.get());
            result = true;
        }
        return result;
    }
}
