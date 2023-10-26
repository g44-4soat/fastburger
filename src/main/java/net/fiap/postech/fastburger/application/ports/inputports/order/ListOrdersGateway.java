package net.fiap.postech.fastburger.application.ports.inputports.order;

import net.fiap.postech.fastburger.application.domain.Order;

import java.util.List;

public interface ListOrdersGateway {
    List<Order> list();
}
