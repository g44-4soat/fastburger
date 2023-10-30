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
import java.util.List;
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
        Order orderParsed = new Order();
        orderParsed.setStatus(order.getStatus());
        if (order.getClientCPF() != null && !order.getClientCPF().isBlank() && !order.getClientCPF().isEmpty()) {
            Client domain = this.clientMapper.toDomain(this.clientRepository.findClientEntityByCpf(order.getClientCPF()).orElseThrow(() -> new ClientNotFoundException("Cliente não encontrado!")));
            orderParsed.setClient(domain);
        }
        return orderParsed;
    }

    public OrderDTO orderToOrderDTO(Order saved) {
        AtomicReference<Double> totalValueOrder = new AtomicReference<>(0.0);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setStatus(saved.getStatus());
        orderDTO.setOrderNumber(saved.getOrderNumber());

        if (saved.getOrderItems() != null) {
            orderDTO.setOrderItens(orderItemToOrderItemDTO(saved.getOrderItems()));
            orderDTO.getOrderItens().forEach(orderItemDTO -> {
                totalValueOrder.updateAndGet(v -> v + orderItemDTO.getSubtotal().doubleValue());
            });
            orderDTO.setTotalValue(BigDecimal.valueOf(totalValueOrder.get()));
        }

        if (saved.getClient() != null)
            orderDTO.setClientCPF(saved.getClient().getCpf());

        return orderDTO;
    }

    public List<OrderItemDTO> orderItemToOrderItemDTO(List<OrderItem> itens) {
        List<OrderItemDTO> orderItemDTOSToReturn = new ArrayList<>();
        itens.forEach(orderItem -> {
            orderItemDTOSToReturn.add(new OrderItemDTO(orderItem.getProductId(), orderItem.getQuantity(), orderItem.getUnitPrice(), orderItem.getSubtotal()));
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
        var clientOrder = this.clientRepository.findById(body.getClient().getId());
        List<ProductEntity> productEntities = new ArrayList<>();

        orderItensDTOS.forEach(productId -> {
            productEntities.add(this.productRepository.findById(productId.getProductId()).get());
        });
        return null;
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
}
