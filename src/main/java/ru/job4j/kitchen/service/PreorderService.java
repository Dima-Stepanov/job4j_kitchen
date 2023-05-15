package ru.job4j.kitchen.service;

import ru.job4j.kitchen.domain.Preorder;
import ru.job4j.kitchen.domain.Status;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j KITCHEN
 * 5. Повествование основанное на хореографии [#458498]
 * PreorderService интерфейс описывает поведение бизнес логики над моделью Preorder
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 14.05.2023
 */
public interface PreorderService {
    Preorder save(Preorder preorder);

    boolean updateStatus(int preorderId, Status status);
}
