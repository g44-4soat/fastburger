package net.fiap.postech.fastburger.adapters.persistence.mapper;

import net.fiap.postech.fastburger.adapters.configuration.exceptionHandler.BusinessException;
import net.fiap.postech.fastburger.adapters.configuration.exceptionHandler.ClientNotFoundException;
import net.fiap.postech.fastburger.adapters.persistence.dto.OrderDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.ProductsOrderDTO;
import net.fiap.postech.fastburger.adapters.persistence.entities.OrderEntity;
import net.fiap.postech.fastburger.adapters.persistence.repositories.ClientRepository;
import net.fiap.postech.fastburger.adapters.persistence.repositories.ProductRepository;
import net.fiap.postech.fastburger.application.domain.Client;
import net.fiap.postech.fastburger.application.domain.Order;
import net.fiap.postech.fastburger.application.domain.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

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
        if (order.getClientId() != null && order.getClientId() != 0) {
            Client domain = this.clientMapper.toDomain(this.clientRepository.findById(order.getClientId()).orElseThrow(() -> new ClientNotFoundException("Cliente não encontrado!")));
            orderParsed.setClient(domain);
        }
        return orderParsed;
    }

    public OrderDTO orderToOrderDTO(Order saved) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setStatus(saved.getStatus());
        orderDTO.setClientId(saved.getClient().getId());
        return orderDTO;
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
}
