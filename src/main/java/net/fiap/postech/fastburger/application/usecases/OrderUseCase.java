package net.fiap.postech.fastburger.application.usecases;

import net.fiap.postech.fastburger.application.domain.Order;
import net.fiap.postech.fastburger.application.ports.inputports.order.*;
import net.fiap.postech.fastburger.application.ports.outputports.order.*;

import java.util.List;

public class OrderUseCase implements DeleteOrderGateway, ListOrderByNumberGateway, ListOrdersGateway, SaveOrderGateway, UpdateOrderGetway {

    private final DeleteOrderOutPutPort deleteOrderOutPutPort;
    private final ListOrderByNumberOutPutPort listOrderByNumberOutPutPort;
    private final ListOrdersOutPutPort listOrdersOutPutPort;
    private final SaveOrderOutPutPort saveOrderOutPutPort;
    private final UpdateOrderOutPutPort updateOrderOutPutPort;

    public OrderUseCase(DeleteOrderOutPutPort deleteOrderOutPutPort,
                        ListOrderByNumberOutPutPort listOrderByNumberOutPutPort,
                        ListOrdersOutPutPort listOrdersOutPutPort,
                        SaveOrderOutPutPort saveOrderOutPutPort,
                        UpdateOrderOutPutPort updateOrderOutPutPort) {
        this.deleteOrderOutPutPort = deleteOrderOutPutPort;
        this.listOrderByNumberOutPutPort = listOrderByNumberOutPutPort;
        this.listOrdersOutPutPort = listOrdersOutPutPort;
        this.saveOrderOutPutPort = saveOrderOutPutPort;
        this.updateOrderOutPutPort = updateOrderOutPutPort;
    }

    @Override
    public Void delete(String id) {
        return this.deleteOrderOutPutPort.delete(id);
    }

    @Override
    public Order listById(String id) {
        return this.listOrderByNumberOutPutPort.listById(id);
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
}
