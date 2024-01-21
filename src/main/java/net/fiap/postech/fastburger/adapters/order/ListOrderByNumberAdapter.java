package net.fiap.postech.fastburger.adapters.order;

import net.fiap.postech.fastburger.adapters.persistence.entities.OrderEntity;
import net.fiap.postech.fastburger.adapters.persistence.mapper.OrderMapper;
import net.fiap.postech.fastburger.adapters.persistence.repositories.OrderRepository;
import net.fiap.postech.fastburger.application.domain.Order;
import net.fiap.postech.fastburger.application.ports.outputports.order.ListOrderByNumberOutPutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListOrderByNumberAdapter implements ListOrderByNumberOutPutPort {
    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    @Autowired
    public ListOrderByNumberAdapter(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public Order listByNumber(String orderNumber) {
        OrderEntity orderEntityByOrderNumber = this.orderRepository.findOrderEntityByOrderNumber(orderNumber);
        return this.orderMapper.orderEntityToOrder(orderEntityByOrderNumber);
    }
}
