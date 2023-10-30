package net.fiap.postech.fastburger.adapters.checkout;

import net.fiap.postech.fastburger.adapters.persistence.dto.PaymentMethodDTO;
import net.fiap.postech.fastburger.application.domain.Order;
import net.fiap.postech.fastburger.application.domain.enums.StatusOrder;
import net.fiap.postech.fastburger.application.ports.inputports.order.ListOrderByNumberGateway;
import net.fiap.postech.fastburger.application.ports.inputports.order.UpdateOrderGetway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FakeCheckoutService {
    private final UpdateOrderGetway updateOrderGetway;
    private final ListOrderByNumberGateway listOrderByNumberGateway;

    @Autowired
    public FakeCheckoutService(UpdateOrderGetway updateOrderGetway, ListOrderByNumberGateway listOrderByNumberGateway) {
        this.updateOrderGetway = updateOrderGetway;
        this.listOrderByNumberGateway = listOrderByNumberGateway;
    }

    public boolean payOrder(String orderNumber, PaymentMethodDTO paymentMethod) {
        Order order = this.listOrderByNumberGateway.listByNumber(orderNumber);
        order.setStatus(StatusOrder.INPREPARATION);
        order.setWasPaid(true);
        Order update = this.updateOrderGetway.update(orderNumber, order);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(update).isPresent();
    }
}
