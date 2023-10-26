package net.fiap.postech.fastburger.application.ports.outputports.order;

import net.fiap.postech.fastburger.application.domain.Order;

public interface UpdateOrderOutPutPort {
    Order update(String id, Order order);
}
