package net.fiap.postech.fastburger.adapters.order;

import net.fiap.postech.fastburger.adapters.persistence.repositories.OrderRepository;
import net.fiap.postech.fastburger.application.ports.outputports.order.ListOrderByIdOutPutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListOrderByIdAdapter implements ListOrderByIdOutPutPort {
    private final OrderRepository orderRepository;

    @Autowired
    public ListOrderByIdAdapter(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Object listById(String id) {
        return this.orderRepository.findById(Long.parseLong(id)).get();
    }
}
