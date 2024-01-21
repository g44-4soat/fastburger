package net.fiap.postech.fastburger.adapters.order;

import net.fiap.postech.fastburger.adapters.persistence.entities.OrderEntity;
import net.fiap.postech.fastburger.adapters.persistence.mapper.OrderMapper;
import net.fiap.postech.fastburger.adapters.persistence.repositories.OrderRepository;
import net.fiap.postech.fastburger.application.domain.Order;
import net.fiap.postech.fastburger.application.domain.enums.StatusOrder;
import net.fiap.postech.fastburger.application.ports.outputports.order.ListOrdersOutPutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListOrdersAdapter implements ListOrdersOutPutPort {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public ListOrdersAdapter(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<Order> list() {
        List<OrderEntity> orderEntityByStatus = this.orderRepository.findAll();
        List<Order> orders = orderEntityByStatus.stream()
                .filter(orderEntity -> !orderEntity.getStatus().statusItemOrder.equalsIgnoreCase(StatusOrder.FINISHED.getStatusItemOrder()))
                .map(this.orderMapper::orderEntityToOrder)
                .sorted(Comparator.comparing(Order::getDateTimeCreation))
                .collect(Collectors.toList());
        return orders;
    }
}
