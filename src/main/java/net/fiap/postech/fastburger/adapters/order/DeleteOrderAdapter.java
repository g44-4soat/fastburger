package net.fiap.postech.fastburger.adapters.order;

import net.fiap.postech.fastburger.adapters.persistence.repositories.OrderRepository;
import net.fiap.postech.fastburger.application.ports.outputports.order.DeleteOrderOutPutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteOrderAdapter implements DeleteOrderOutPutPort {
    private final OrderRepository orderRepository;

    @Autowired
    public DeleteOrderAdapter(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Void delete(String id) {
        this.orderRepository.deleteById(Long.parseLong(id));
        return null;
    }
}
