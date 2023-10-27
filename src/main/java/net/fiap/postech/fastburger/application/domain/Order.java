package net.fiap.postech.fastburger.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.fiap.postech.fastburger.application.domain.enums.StatusOrder;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    private String id;
    private String orderNumber;
    private Double totalValue;
    private LocalDateTime dateTimeCreation;
    private Client client;
    private List<Product> products;
    private StatusOrder status;
}
