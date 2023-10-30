package net.fiap.postech.fastburger.adapters.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.fiap.postech.fastburger.adapters.persistence.dto.OrderDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.OrderItemDTO;
import net.fiap.postech.fastburger.adapters.persistence.mapper.OrderMapper;
import net.fiap.postech.fastburger.application.domain.Order;
import net.fiap.postech.fastburger.application.ports.inputports.order.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@Tag(name = "Order Controller Rest")
public class OrderController {
    private final DeleteOrderGateway deleteOrderGateway;
    private final ListOrderByNumberGateway listOrderByNumberGateway;
    private final ListOrdersGateway listOrdersGateway;
    private final SaveOrderGateway saveOrderGateway;
    private final UpdateOrderGetway updateOrderGetway;

    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(DeleteOrderGateway deleteOrderGateway,
                           ListOrderByNumberGateway listOrderByNumberGateway,
                           OrderMapper orderMapper,
                           ListOrdersGateway listOrdersGateway, SaveOrderGateway saveOrderGateway, UpdateOrderGetway updateOrderGetway) {
        this.deleteOrderGateway = deleteOrderGateway;
        this.listOrderByNumberGateway = listOrderByNumberGateway;
        this.listOrdersGateway = listOrdersGateway;
        this.saveOrderGateway = saveOrderGateway;
        this.updateOrderGetway = updateOrderGetway;
        this.orderMapper = orderMapper;
    }

    @GetMapping("orderNumber/{orderNumber}")
    public ResponseEntity<OrderDTO> findOrderByNumber(@PathVariable("orderNumber") String orderNumber) {
        Order order = this.listOrderByNumberGateway.listByNumber(orderNumber);
        return ResponseEntity.ok(this.orderMapper.orderToOrderDTO(order));
    }

    @PostMapping("/create")
    public ResponseEntity saveOrder(@RequestBody OrderDTO order) {
        Order saved = this.saveOrderGateway.save(this.orderMapper.orderDTOToOrder(order));
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/lanche/order/{orderNumber}")
    public ResponseEntity saveLancheOnOrder(@PathVariable("orderNumber") String orderNumber, @RequestBody List<OrderItemDTO> orderItensDTOS) {
        Order orderToUpdate = this.orderMapper.toUpdateOrderWithITens(this.orderMapper.orderDTOToOrder(this.findOrderByNumber(orderNumber).getBody()), orderItensDTOS);
        return ResponseEntity.ok(this.updateOrderGetway.update(orderNumber, orderToUpdate));
    }
/**
 @DeleteMapping("/{id}") public void deleteOrder(@PathVariable("id") Long id) {
 this.orderRepository.delete(this.orderRepository.getReferenceById(id));
 }

 @GetMapping public ResponseEntity findAllOrders() {
 return ResponseEntity.ok(this.orderRepository.findAll());
 } **/
}
