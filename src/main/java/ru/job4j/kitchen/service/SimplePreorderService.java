package ru.job4j.kitchen.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.job4j.kitchen.domain.Preorder;
import ru.job4j.kitchen.domain.Status;
import ru.job4j.kitchen.repository.PreorderRepository;
import ru.job4j.kitchen.repository.StatusRepository;

/**
 * 3. Мидл
 * 3.5. Микросервисы
 * Job4j Hungry Wolf
 * Job4j KITCHEN
 * 5. Повествование основанное на хореографии [#458498]
 * SimplePreorderServiceService слой бизнес логики обработки модели Preorder
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 14.05.2023
 */
@Service
@AllArgsConstructor
@Slf4j
public class SimplePreorderService implements PreorderService {
    private final PreorderRepository preorderRepository;
    private final StatusRepository statusRepository;

    @Override
    public Preorder save(Preorder preorder) {
        try {
            preorderRepository.save(preorder);
            return preorder;
        } catch (Exception e) {
            log.error("Preorder: {}, saved error: {}", preorder, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateStatus(int preorderId, Status status) {
        var preorder = preorderRepository.findById(preorderId);
        if (preorder.isPresent()) {
            preorder.get().setStatus(status);
            preorderRepository.save(preorder.get());
            return true;
        }
        return false;
    }
}
