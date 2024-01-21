package net.fiap.postech.fastburger.application.ports.outputports.order;

import net.fiap.postech.fastburger.application.domain.Order;

import java.util.List;

public interface ListOrdersOutPutPort {
    List<Order> list();
}
