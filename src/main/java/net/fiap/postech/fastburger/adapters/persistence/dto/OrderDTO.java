package net.fiap.postech.fastburger.adapters.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.fiap.postech.fastburger.application.domain.enums.StatusOrder;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String orderNumber;
    private StatusOrder status;
    private String clientCPF;
    private BigDecimal totalValue;
    private Boolean wasPaid = false;
    private List<OrderItemDTO> orderItens;
}
