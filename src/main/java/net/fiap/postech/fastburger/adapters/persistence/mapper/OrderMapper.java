package net.fiap.postech.fastburger.adapters.persistence.mapper;

import net.fiap.postech.fastburger.adapters.configuration.exceptionHandler.BusinessException;
import net.fiap.postech.fastburger.adapters.configuration.exceptionHandler.ClientNotFoundException;
import net.fiap.postech.fastburger.adapters.persistence.dto.OrderDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.OrderItemDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.OrderRequestDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.ProductsOrderDTO;
import net.fiap.postech.fastburger.adapters.persistence.entities.OrderEntity;
import net.fiap.postech.fastburger.adapters.persistence.entities.ProductEntity;
import net.fiap.postech.fastburger.adapters.persistence.repositories.ClientRepository;
import net.fiap.postech.fastburger.adapters.persistence.repositories.OrderRepository;
import net.fiap.postech.fastburger.adapters.persistence.repositories.ProductRepository;
import net.fiap.postech.fastburger.application.domain.Client;
import net.fiap.postech.fastburger.application.domain.Order;
import net.fiap.postech.fastburger.application.domain.OrderItem;
import net.fiap.postech.fastburger.application.domain.Product;
import net.fiap.postech.fastburger.application.domain.enums.StatusOrder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class OrderMapper {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ClientMapper clientMapper;

    public OrderEntity orderToOrderEntity(Order order) {
        return modelMapper.map(order, OrderEntity.class);
    }

    public Order orderEntityToOrder(OrderEntity orderSaved) {
        return modelMapper.map(orderSaved, Order.class);
    }

    public Order orderDTOToOrder(OrderDTO order) {
        return modelMapper.map(order, Order.class);
    }

    public OrderDTO orderToOrderDTO(Order saved) {
        AtomicReference<Double> totalValueOrder = new AtomicReference<>(0.0);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setStatus(saved.getStatus());
        orderDTO.setOrderNumber(saved.getOrderNumber());
        orderDTO.setWasPaid(saved.getWasPaid());

        if (saved.getOrderItems() != null) {
            orderDTO.setOrderItens(orderItemToOrderItemDTO(saved.getOrderItems()));
            if (saved.getTotalValue() != null)
                orderDTO.setTotalValue(BigDecimal.valueOf(saved.getTotalValue().doubleValue()));
        }

        if (saved.getClient() != null)
            orderDTO.setClientCPF(saved.getClient().getCpf());

        return orderDTO;
    }

    public List<OrderItemDTO> orderItemToOrderItemDTO(List<OrderItem> itens) {
        List<OrderItemDTO> orderItemDTOSToReturn = new ArrayList<>();
        itens.forEach(orderItem -> {
            orderItemDTOSToReturn.add(new OrderItemDTO(orderItem.getProductId(), orderItem.getQuantity()));
        });
        return orderItemDTOSToReturn;
    }

    public Order toUpdateOrder(Order body, List<ProductsOrderDTO> productsOrderDTO) {
        AtomicReference<Double> valorTotal = new AtomicReference<>(0.0);
        List<Product> products = new ArrayList<>();
        if (productsOrderDTO.isEmpty()) {
            throw new BusinessException("Sem produtos no pedido");
        }
        productsOrderDTO.forEach(item -> {
            Product domain = this.productMapper.toDomain(this.productRepository.findById(item.getProductId()).get());
            products.add(domain);
        });
        return body;
    }

    public Order toUpdateOrderWithITens(Order body, List<OrderItemDTO> orderItensDTOS) {
        OrderEntity orderEntity = this.orderRepository.findOrderEntityByOrderNumber(body.getOrderNumber());
        AtomicReference<Double> valorTotal = new AtomicReference<>(0.0);
        List<OrderItem> orderItems = new ArrayList<>();

        if (body.getClient() != null) {
            var clientOrder = this.clientRepository.findClientEntityByCpf(body.getClient().getCpf());
        }

        if (body.getOrderItems().isEmpty()) {
            orderItensDTOS.forEach(product -> {
                ProductEntity productEntity = this.productRepository.findById(product.getProductId()).get();
                orderItems.add(new OrderItem(null, productEntity.getSKU(), product.getQuantity()));
            });
            body.setOrderItems(orderItems);

        } else {
            orderItensDTOS.forEach(product -> {
                ProductEntity productEntity = this.productRepository.findById(product.getProductId()).get();
                body.getOrderItems().add(new OrderItem(null, productEntity.getSKU(), product.getQuantity()));
            });
        }

        orderItensDTOS.forEach(orderItem -> {
            Double price = this.productRepository.findById(orderItem.getProductId()).get().getPrice();
            Integer quantity = orderItem.getQuantity();
            valorTotal.updateAndGet(x -> x + (quantity * price));
        });

        if (orderEntity.getTotalValue() != null) {
            valorTotal.updateAndGet(x -> x + orderEntity.getTotalValue());
        }
        body.setTotalValue(BigDecimal.valueOf(valorTotal.get()));
        return body;
    }

    public Order orderRequestDTOToOrder(OrderRequestDTO order) {
        Order orderParsed = new Order();
        orderParsed.setStatus(StatusOrder.RECEIVED);
        if (order.getClientCPF() != null && !order.getClientCPF().isBlank() && !order.getClientCPF().isEmpty()) {
            Client domain = this.clientMapper.toDomain(this.clientRepository.findClientEntityByCpf(order.getClientCPF()).orElseThrow(() -> new ClientNotFoundException("Cliente não encontrado!")));
            orderParsed.setClient(domain);
        }
        return orderParsed;
    }

    public Order mapOrderToReady(Order order) {
        order.setStatus(StatusOrder.READY);
        return order;
    }

    public Order mapOrderToFinished(Order order) {
        order.setStatus(StatusOrder.FINISHED);
        return order;
    }
}
