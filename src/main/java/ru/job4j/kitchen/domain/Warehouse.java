package ru.job4j.kitchen.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j KITCHEN
 * 5. Повествование основанное на хореографии [#458498]
 * Warehouse обобщенный склад продуктов для приготовления блюда.
 * dishId код блюда из сервиса job4j_dish
 * dishNum содержит количество блюд которые может приготовить кухня
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 16.05.2023
 */
@Entity
@Table(name = "WAREHOUSES")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Warehouse {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "DISH_ID")
    private int dishId;
    @Column(name = "DISH_NUM")
    private int dishNum;
}
