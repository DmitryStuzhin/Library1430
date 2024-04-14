package ru.testRestApi.project.RESTApiLIbraryProject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.testRestApi.project.RESTApiLIbraryProject.models.ActiveOrder;
import ru.testRestApi.project.RESTApiLIbraryProject.repositoryes.ActiveOrderRepository;

@Service
@RequiredArgsConstructor
public class ActiveOrderService {
    private final ActiveOrderRepository activeOrderRepository;

    public void save(ActiveOrder activeOrder) { activeOrderRepository.save(activeOrder); }

    public ActiveOrder isActiveOrderByOrder_Number(Integer order_number){
        return activeOrderRepository.findActiveOrdersByOrderNumber(order_number).orElse(null);
    }
}
