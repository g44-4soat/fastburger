package net.fiap.postech.fastburger.adapters.order;

import net.fiap.postech.fastburger.adapters.persistence.entities.OrderEntity;
import net.fiap.postech.fastburger.adapters.persistence.mapper.OrderMapper;
import net.fiap.postech.fastburger.adapters.persistence.repositories.OrderRepository;
import net.fiap.postech.fastburger.application.domain.Order;
import net.fiap.postech.fastburger.application.domain.enums.StatusOrder;
import net.fiap.postech.fastburger.application.ports.outputports.order.ListOrderByIdOutPutPort;
import net.fiap.postech.fastburger.application.ports.outputports.order.ListOrderByStatusOutPutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListOrderByStatusAdapter implements ListOrderByStatusOutPutPort {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public ListOrderByStatusAdapter(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<Order> listByStatus(StatusOrder statusOrder) {
        List<OrderEntity> orderEntityByStatus = this.orderRepository.findOrderEntityByStatus(statusOrder);
        List<Order> orders = orderEntityByStatus.stream()
                .map(this.orderMapper::orderEntityToOrder)
                .sorted(Comparator.comparing(Order::getDateTimeCreation))
                .collect(Collectors.toList());
        return orders;
    }
}
