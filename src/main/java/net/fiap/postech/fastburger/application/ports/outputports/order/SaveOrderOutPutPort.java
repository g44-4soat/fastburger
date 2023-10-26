package net.fiap.postech.fastburger.application.ports.outputports.order;

import net.fiap.postech.fastburger.application.domain.Order;

public interface SaveOrderOutPutPort {
    Order save(Order order);
}
