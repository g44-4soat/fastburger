package net.fiap.postech.fastburger.adapters.order;

import net.fiap.postech.fastburger.adapters.persistence.entities.OrderEntity;
import net.fiap.postech.fastburger.adapters.persistence.mapper.OrderMapper;
import net.fiap.postech.fastburger.adapters.persistence.repositories.OrderRepository;
import net.fiap.postech.fastburger.application.domain.Order;
import net.fiap.postech.fastburger.application.ports.outputports.order.UpdateOrderOutPutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;

@Component
public class UpdateOrderAdapter implements UpdateOrderOutPutPort {
    private final OrderRepository orderRepository;
    private final ListOrderByNumberAdapter listOrderByIdAdapter;
    private final OrderMapper orderMapper;

    @Autowired
    public UpdateOrderAdapter(OrderRepository orderRepository, ListOrderByNumberAdapter listOrderByIdAdapter, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.listOrderByIdAdapter = listOrderByIdAdapter;
        this.orderMapper = orderMapper;
    }

    @Override
    public Order update(String orderNumber, Order order) {
        var oldOrder = this.orderRepository.findOrderEntityByOrderNumber(orderNumber);
        order.setId(oldOrder.getId().toString());
        OrderEntity saved = this.orderRepository.save(this.orderMapper.orderToOrderEntity(order));
        return this.orderMapper.orderEntityToOrder(saved);
    }
}
