package net.fiap.postech.fastburger.application.usecases;

import net.fiap.postech.fastburger.application.ports.inputports.order.*;
import net.fiap.postech.fastburger.application.ports.outputports.order.*;

import java.util.List;

public class OrderUseCase implements DeleteOrderGateway, ListOrderByIdGateway, ListOrdersGateway, SaveOrderGateway, UpdateOrderGetway {

    private final DeleteOrderOutPutPort deleteOrderOutPutPort;
    private final ListOrderByIdOutPutPort listOrderByIdOutPutPort;
    private final ListOrdersOutPutPort listOrdersOutPutPort;
    private final SaveOrderOutPutPort saveOrderOutPutPort;
    private final UpdateOrderOutPutPort updateOrderOutPutPort;

    public OrderUseCase(DeleteOrderOutPutPort deleteOrderOutPutPort,
                        ListOrderByIdOutPutPort listOrderByIdOutPutPort,
                        ListOrdersOutPutPort listOrdersOutPutPort,
                        SaveOrderOutPutPort saveOrderOutPutPort,
                        UpdateOrderOutPutPort updateOrderOutPutPort) {
        this.deleteOrderOutPutPort = deleteOrderOutPutPort;
        this.listOrderByIdOutPutPort = listOrderByIdOutPutPort;
        this.listOrdersOutPutPort = listOrdersOutPutPort;
        this.saveOrderOutPutPort = saveOrderOutPutPort;
        this.updateOrderOutPutPort = updateOrderOutPutPort;
    }

    @Override
    public Void delete(String id) {
        return this.deleteOrderOutPutPort.delete(id);
    }

    @Override
    public Object listById(String id) {
        return this.listOrderByIdOutPutPort.listById(id);
    }

    @Override
    public List<Object> list() {
        return this.listOrdersOutPutPort.list();
    }

    @Override
    public Object save(Object order) {
        return this.saveOrderOutPutPort.save(order);
    }

    @Override
    public Object update(String id, Object order) {
        return this.updateOrderOutPutPort.update(id, order);
    }
}
