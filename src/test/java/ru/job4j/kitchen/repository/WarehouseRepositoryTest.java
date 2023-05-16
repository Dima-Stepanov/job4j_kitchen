package ru.job4j.kitchen.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.job4j.kitchen.domain.Warehouse;

import javax.persistence.EntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j KITCHEN
 * 5. Повествование основанное на хореографии [#458498]
 * WarehouseRepositoryTest
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 16.05.2023
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest()
class WarehouseRepositoryTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @BeforeEach
    public void initTest() {
        entityManager.createQuery("delete from Warehouse").executeUpdate();
    }

    @Test
    public void whenGetWarehouseByDishIdAndDishNumGreatNullThenReturnFirs() {
        var warehouse = new Warehouse(0, 1, 3);
        var warehouse1 = new Warehouse(0, 1, 0);
        warehouseRepository.save(warehouse);
        warehouseRepository.save(warehouse1);

        var result = warehouseRepository.getWarehouseByDishId(warehouse.getDishId());
        assertThat(warehouse).isEqualTo(result.get());
    }
}