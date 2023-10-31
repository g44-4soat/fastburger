package net.fiap.postech.fastburger.application.usecases;

import net.fiap.postech.fastburger.application.domain.Order;
import net.fiap.postech.fastburger.application.domain.enums.StatusOrder;
import net.fiap.postech.fastburger.application.ports.inputports.order.*;
import net.fiap.postech.fastburger.application.ports.outputports.order.*;

import java.util.List;

public class OrderUseCase implements DeleteOrderGateway, ListOrderByNumberGateway, ListOrdersGateway, SaveOrderGateway, UpdateOrderGetway, ListOrderByIdGateway, ListOrderByStatusGateway {
    private final DeleteOrderOutPutPort deleteOrderOutPutPort;
    private final ListOrderByNumberOutPutPort listOrderByNumberOutPutPort;
    private final ListOrdersOutPutPort listOrdersOutPutPort;
    private final SaveOrderOutPutPort saveOrderOutPutPort;
    private final UpdateOrderOutPutPort updateOrderOutPutPort;
    private final ListOrderByIdOutPutPort listOrderByIdOutPutPort;

    private final ListOrderByStatusOutPutPort listOrderByStatusOutPutPort;

    public OrderUseCase(DeleteOrderOutPutPort deleteOrderOutPutPort, ListOrderByNumberOutPutPort listOrderByNumberOutPutPort,
                        ListOrdersOutPutPort listOrdersOutPutPort, SaveOrderOutPutPort saveOrderOutPutPort, UpdateOrderOutPutPort updateOrderOutPutPort,
                        ListOrderByIdOutPutPort listOrderByIdOutPutPort,
                        ListOrderByStatusOutPutPort listOrderByStatusOutPutPort) {
        this.deleteOrderOutPutPort = deleteOrderOutPutPort;
        this.listOrderByNumberOutPutPort = listOrderByNumberOutPutPort;
        this.listOrdersOutPutPort = listOrdersOutPutPort;
        this.saveOrderOutPutPort = saveOrderOutPutPort;
        this.updateOrderOutPutPort = updateOrderOutPutPort;
        this.listOrderByIdOutPutPort = listOrderByIdOutPutPort;
        this.listOrderByStatusOutPutPort = listOrderByStatusOutPutPort;
    }

    @Override
    public Void delete(String id) {
        return this.deleteOrderOutPutPort.delete(id);
    }

    @Override
    public Order listByNumber(String orderNumber) {
        return this.listOrderByNumberOutPutPort.listByNumber(orderNumber);
    }

    @Override
    public List<Order> list() {
        return this.listOrdersOutPutPort.list();
    }

    @Override
    public Order save(Order order) {
        return this.saveOrderOutPutPort.save(order);
    }

    @Override
    public Order update(String id, Order order) {
        return this.updateOrderOutPutPort.update(id, order);
    }

    @Override
    public Order listById(String id) {
        return this.listOrderByIdOutPutPort.listById(id);
    }

    @Override
    public List<Order> listByStatus(StatusOrder statusOrder) {
        return this.listOrderByStatusOutPutPort.listByStatus(statusOrder);
    }
}
