package net.fiap.postech.fastburger.application.ports.inputports.order;

import net.fiap.postech.fastburger.application.domain.Order;

public interface UpdateOrderGetway {
    Order update(String orderNumber, Order order);
}
