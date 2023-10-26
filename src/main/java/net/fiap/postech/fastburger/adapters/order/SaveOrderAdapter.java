package net.fiap.postech.fastburger.adapters.order;

import net.fiap.postech.fastburger.adapters.history.SaveHistoryAdapter;
import net.fiap.postech.fastburger.adapters.persistence.repositories.OrderRepository;
import net.fiap.postech.fastburger.application.domain.HistoryOfOrder;
import net.fiap.postech.fastburger.application.domain.Order;
import net.fiap.postech.fastburger.application.ports.outputports.order.SaveOrderOutPutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SaveOrderAdapter implements SaveOrderOutPutPort {
    private final OrderRepository orderRepository;
    private final SaveHistoryAdapter saveHistoryAdapter;

    @Autowired
    public SaveOrderAdapter(OrderRepository orderRepository, SaveHistoryAdapter saveHistoryAdapter) {
        this.orderRepository = orderRepository;
        this.saveHistoryAdapter = saveHistoryAdapter;
    }

    @Override
    public Order save(Order order) {
        var lastOrderId = this.orderRepository.getLastOrderId();
        order.setOrderNumber(generateOrderNumber().concat(lastOrderId.toString()));
        this.saveHistoryAdapter.save(new HistoryOfOrder(null, order.getStatus(), Long.parseLong(order.getId())));

        return null;
    }

    private String generateOrderNumber() {
        return String.format("%02d%02d%d", LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear());
    }
}
