package net.fiap.postech.fastburger.adapters.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.fiap.postech.fastburger.adapters.persistence.dto.OrderDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.OrderItensDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.ProductsOrderDTO;
import net.fiap.postech.fastburger.adapters.persistence.entities.OrderEntity;
import net.fiap.postech.fastburger.adapters.persistence.mapper.OrderMapper;
import net.fiap.postech.fastburger.adapters.persistence.repositories.OrderRepository;
import net.fiap.postech.fastburger.application.domain.Order;
import net.fiap.postech.fastburger.application.ports.inputports.order.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Order> findOrderByNumber(@PathVariable("orderNumber") String orderNumber) {
        return ResponseEntity.ok(this.listOrderByNumberGateway.listByNumber(orderNumber));
    }

    @PostMapping("/create")
    public ResponseEntity saveOrder(@RequestBody OrderDTO order) {
        Order saved = this.saveOrderGateway.save(this.orderMapper.orderDTOToOrder(order));
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/lanche/order/{orderNumber}")
    public ResponseEntity saveLancheOnOrder(@PathVariable("orderNumber") String orderNumber, @RequestBody ProductsOrderDTO productsOrderDTO) {
        Order orderToUpdate = this.orderMapper.toUpdateOrder(this.findOrderByNumber(orderNumber).getBody(), productsOrderDTO);
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
