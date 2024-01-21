package net.fiap.postech.fastburger.application.ports.outputports.order;

import net.fiap.postech.fastburger.application.domain.Order;

public interface ListOrderByNumberOutPutPort {
    Order listByNumber(String orderNumber);
}
