package net.fiap.postech.fastburger.adapters.order;

import net.fiap.postech.fastburger.adapters.persistence.repositories.OrderRepository;
import net.fiap.postech.fastburger.application.domain.Order;
import net.fiap.postech.fastburger.application.ports.outputports.order.ListOrderByIdOutPutPort;
import net.fiap.postech.fastburger.application.ports.outputports.order.ListOrderByNumberOutPutPort;
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
    public Order listById(String id) {
        return null;
    }
}
