package net.fiap.postech.fastburger.adapters.configuration.order;

import net.fiap.postech.fastburger.adapters.order.*;
import net.fiap.postech.fastburger.application.usecases.OrderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Orderconfig {

    @Bean
    public OrderUseCase orderUseCase(
            DeleteOrderAdapter deleteOrderAdapter,
            ListOrderByNumberAdapter listOrderByNumberAdapter,
            ListOrdersAdapter listOrdersAdapter,
            SaveOrderAdapter saveOrderAdapter,
            UpdateOrderAdapter updateOrderAdapter,
            ListOrderByIdAdapter listOrderByIdAdapter,
            ListOrderByStatusAdapter listOrderByStatusAdapter
    ) {
        return new OrderUseCase(deleteOrderAdapter, listOrderByNumberAdapter,
                listOrdersAdapter, saveOrderAdapter, updateOrderAdapter, listOrderByIdAdapter, listOrderByStatusAdapter);
    }
}
