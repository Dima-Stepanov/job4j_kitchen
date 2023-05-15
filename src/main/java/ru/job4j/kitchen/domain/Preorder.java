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
 * Preorder модель данных, описывает заказ для кухни на приготовления блюда
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 12.05.2023
 */
@Entity
@Table(name = "PREORDERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Preorder {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ORDER_ID")
    private int orderId;
    @ManyToOne()
    @JoinColumn(name = "STATUS_ID", foreignKey = @ForeignKey(name = "FK_STATUS_ID"))
    private Status status;
    @Column(name = "DISH_ID")
    private int dishId;
}
