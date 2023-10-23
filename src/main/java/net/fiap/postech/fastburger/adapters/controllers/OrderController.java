package net.fiap.postech.fastburger.adapters.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.fiap.postech.fastburger.application.ports.inputports.order.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/order")
@Tag(name = "Order Controller Rest")
public class OrderController {
    private final DeleteOrderGateway deleteOrderGateway;
    private final ListOrderByIdGateway listOrderByIdGateway;
    private final ListOrdersGateway listOrdersGateway;
    private final SaveOrderGateway saveOrderGateway;
    private final UpdateOrderGetway updateOrderGetway;

    @Autowired
    public OrderController(DeleteOrderGateway deleteOrderGateway, ListOrderByIdGateway listOrderByIdGateway, ListOrdersGateway listOrdersGateway, SaveOrderGateway saveOrderGateway, UpdateOrderGetway updateOrderGetway) {
        this.deleteOrderGateway = deleteOrderGateway;
        this.listOrderByIdGateway = listOrderByIdGateway;
        this.listOrdersGateway = listOrdersGateway;
        this.saveOrderGateway = saveOrderGateway;
        this.updateOrderGetway = updateOrderGetway;
    }

    @PostMapping
    public ResponseEntity saveOrder(@RequestBody Object order) {
        return ResponseEntity.ok().build();
    }
}
