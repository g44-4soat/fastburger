package net.fiap.postech.fastburger.adapters.order;

import net.fiap.postech.fastburger.adapters.persistence.repositories.OrderRepository;
import net.fiap.postech.fastburger.application.ports.outputports.order.SaveOrderOutPutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveOrderAdapter implements SaveOrderOutPutPort {
    private final OrderRepository orderRepository;

    @Autowired
    public SaveOrderAdapter(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Object save(Object order) {
        // return this.orderRepository.save(order);
        return null;
    }
}
