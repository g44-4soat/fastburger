package net.fiap.postech.fastburger.adapters.order;

import net.fiap.postech.fastburger.adapters.persistence.repositories.OrderRepository;
import net.fiap.postech.fastburger.application.domain.Order;
import net.fiap.postech.fastburger.application.ports.outputports.order.ListOrderByNumberOutPutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListOrderByNumberAdapter implements ListOrderByNumberOutPutPort {
    private final OrderRepository orderRepository;

    @Autowired
    public ListOrderByNumberAdapter(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order listById(String id) {
        //  return this.orderRepository.findById(Long.parseLong(id)).get();
        return null;
    }
}
