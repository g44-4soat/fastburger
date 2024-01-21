package net.fiap.postech.fastburger.application.ports.outputports.order;

import net.fiap.postech.fastburger.application.domain.Order;
import net.fiap.postech.fastburger.application.domain.enums.StatusOrder;

import java.util.List;

public interface ListOrderByStatusOutPutPort {
    List<Order> listByStatus(StatusOrder statusOrder);
}
