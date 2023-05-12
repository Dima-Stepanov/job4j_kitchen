package ru.job4j.kitchen.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j KITCHEN
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 04.05.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private int id;
    private String name;
    private String description;
    private int statusId;
    private String status;
    private int dishId;
    private String dishName;
}
