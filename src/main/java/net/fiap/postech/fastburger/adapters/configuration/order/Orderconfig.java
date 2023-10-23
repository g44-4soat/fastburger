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
            ListOrderByIdAdapter listOrderByIdAdapter,
            ListOrdersAdapter listOrdersAdapter,
            SaveOrderAdapter saveOrderAdapter,
            UpdateOrderAdapter updateOrderAdapter
    ) {
        return new OrderUseCase(deleteOrderAdapter, listOrderByIdAdapter,
                listOrdersAdapter, saveOrderAdapter, updateOrderAdapter);
    }
}
