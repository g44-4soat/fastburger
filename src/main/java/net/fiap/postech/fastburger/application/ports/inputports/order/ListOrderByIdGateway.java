package net.fiap.postech.fastburger.application.ports.inputports.order;

import net.fiap.postech.fastburger.application.domain.Order;

public interface ListOrderByIdGateway {
    Order listById(String id);
}
