package net.fiap.postech.fastburger.adapters.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.fiap.postech.fastburger.adapters.persistence.dto.OrderDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.OrderItemDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.OrderRequestDTO;
import net.fiap.postech.fastburger.adapters.persistence.mapper.OrderMapper;
import net.fiap.postech.fastburger.application.domain.Order;
import net.fiap.postech.fastburger.application.domain.enums.StatusOrder;
import net.fiap.postech.fastburger.application.ports.inputports.order.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    private final ListOrderByStatusGateway listOrderByStatusGateway;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(DeleteOrderGateway deleteOrderGateway,
                           ListOrderByNumberGateway listOrderByNumberGateway,
                           OrderMapper orderMapper,
                           ListOrderByStatusGateway listOrderByStatusGateway,
                           ListOrdersGateway listOrdersGateway, SaveOrderGateway saveOrderGateway, UpdateOrderGetway updateOrderGetway) {
        this.deleteOrderGateway = deleteOrderGateway;
        this.listOrderByNumberGateway = listOrderByNumberGateway;
        this.listOrdersGateway = listOrdersGateway;
        this.saveOrderGateway = saveOrderGateway;
        this.updateOrderGetway = updateOrderGetway;
        this.orderMapper = orderMapper;
        this.listOrderByStatusGateway = listOrderByStatusGateway;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findOrders() {
        List<Order> orders = this.listOrdersGateway.list();
        List<OrderDTO> orderDTOS = new ArrayList<>();
        orders.forEach(order -> orderDTOS.add(this.orderMapper.orderToOrderDTO(order)));
        return ResponseEntity.ok(orderDTOS);
    }

    @GetMapping("orderNumber/{orderNumber}")
    public ResponseEntity<OrderDTO> findOrderByNumber(@PathVariable("orderNumber") String orderNumber) {
        Order order = this.listOrderByNumberGateway.listByNumber(orderNumber);
        return ResponseEntity.ok(this.orderMapper.orderToOrderDTO(order));
    }

    @GetMapping("/{status}")
    public ResponseEntity<List<OrderDTO>> findOrdersByStatus(@PathVariable("status") StatusOrder statusOrder) {
        List<Order> orders = this.listOrderByStatusGateway.listByStatus(statusOrder);
        List<OrderDTO> orderDTOS = new ArrayList<>();
        orders.forEach(order -> orderDTOS.add(this.orderMapper.orderToOrderDTO(order)));
        return ResponseEntity.ok(orderDTOS);
    }

    @PostMapping("/create")
    public ResponseEntity saveOrder(@RequestBody OrderRequestDTO order) {
        Order saved = this.saveOrderGateway.save(this.orderMapper.orderRequestDTOToOrder(order));
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/lanche/order/{orderNumber}")
    public ResponseEntity saveLancheOnOrder(@PathVariable("orderNumber") String orderNumber, @RequestBody List<OrderItemDTO> orderItensDTOS) {
        Order order = this.listOrderByNumberGateway.listByNumber(orderNumber);
        Order orderToUpdate = this.orderMapper.toUpdateOrderWithITens(order, orderItensDTOS);
        return ResponseEntity.ok(this.updateOrderGetway.update(orderNumber, orderToUpdate));
    }

    @PutMapping("/acompanhamento/order/{orderNumber}")
    public ResponseEntity saveAcompanhamentoOnOrder(@PathVariable("orderNumber") String orderNumber, @RequestBody List<OrderItemDTO> orderItensDTOS) {
        Order order = this.listOrderByNumberGateway.listByNumber(orderNumber);
        Order orderToUpdate = this.orderMapper.toUpdateOrderWithITens(order, orderItensDTOS);
        return ResponseEntity.ok(this.updateOrderGetway.update(orderNumber, orderToUpdate));
    }

    @PutMapping("/bebida/order/{orderNumber}")
    public ResponseEntity saveBebidaOnOrder(@PathVariable("orderNumber") String orderNumber, @RequestBody List<OrderItemDTO> orderItensDTOS) {
        Order order = this.listOrderByNumberGateway.listByNumber(orderNumber);
        Order orderToUpdate = this.orderMapper.toUpdateOrderWithITens(order, orderItensDTOS);
        return ResponseEntity.ok(this.updateOrderGetway.update(orderNumber, orderToUpdate));
    }

    @PutMapping("/order/ready/{orderNumber}")
    public ResponseEntity orderReady(@PathVariable("orderNumber") String orderNumber) {
        Order order = this.listOrderByNumberGateway.listByNumber(orderNumber);
        return ResponseEntity.ok(this.updateOrderGetway.update(orderNumber, this.orderMapper.mapOrderToReady(order)));
    }

    @PutMapping("/order/finished/{orderNumber}")
    public ResponseEntity saveBebidaOnOrder(@PathVariable("orderNumber") String orderNumber) {
        Order order = this.listOrderByNumberGateway.listByNumber(orderNumber);
        return ResponseEntity.ok(this.updateOrderGetway.update(orderNumber, this.orderMapper.mapOrderToFinished(order)));
    }

}
