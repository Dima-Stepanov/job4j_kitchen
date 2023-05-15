package ru.job4j.kitchen.mapper;

import ru.job4j.kitchen.domain.Preorder;
import ru.job4j.kitchen.domain.Status;
import ru.job4j.kitchen.domain.dto.OrderDTO;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j KITCHEN
 * 5. Повествование основанное на хореографии [#458498]
 * PreorderMapper класс для преобразования DAO Preorder в DTO Order и наоборот.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 14.05.2023
 */
public class PreorderMapper {

    /**
     * Преобразование OrderDTO в Preorder
     *
     * @param orderDTO OrderDTO
     * @return Preorder
     */
    public Preorder getPreorderByOrderDTO(OrderDTO orderDTO) {
        return new Preorder(0, orderDTO.getId(),
                new Status(orderDTO.getStatusId(), orderDTO.getStatus()),
                orderDTO.getDishId());
    }
}
