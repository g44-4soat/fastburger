package net.fiap.postech.fastburger.adapters.order;

import net.fiap.postech.fastburger.adapters.persistence.repositories.OrderRepository;
import net.fiap.postech.fastburger.application.domain.Order;
import net.fiap.postech.fastburger.application.ports.outputports.order.ListOrdersOutPutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListOrdersAdapter implements ListOrdersOutPutPort {
    private final OrderRepository orderRepository;

    @Autowired
    public ListOrdersAdapter(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> list() {
        // return this.orderRepository.findAll();
        return null;
    }
}
