package net.fiap.postech.fastburger.adapters.order;

import net.fiap.postech.fastburger.adapters.persistence.repositories.OrderRepository;
import net.fiap.postech.fastburger.application.ports.outputports.order.UpdateOrderOutPutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateOrderAdapter implements UpdateOrderOutPutPort {
    private final OrderRepository orderRepository;
    private final ListOrderByIdAdapter listOrderByIdAdapter;

    @Autowired
    public UpdateOrderAdapter(OrderRepository orderRepository, ListOrderByIdAdapter listOrderByIdAdapter) {
        this.orderRepository = orderRepository;
        this.listOrderByIdAdapter = listOrderByIdAdapter;
    }

    @Override
    public Object update(String id, Object order) {
        var orderToUpdate = listOrderByIdAdapter.listById(id);
        // order.setIt(orderToUpdate.getId());
        return this.orderRepository.save(order);
    }
}
