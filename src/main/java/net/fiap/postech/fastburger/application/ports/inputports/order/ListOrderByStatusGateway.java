package net.fiap.postech.fastburger.application.ports.inputports.order;

import net.fiap.postech.fastburger.application.domain.Order;
import net.fiap.postech.fastburger.application.domain.enums.StatusOrder;

import java.util.List;

public interface ListOrderByStatusGateway {
    List<Order> listByStatus(StatusOrder statusOrder);
}
