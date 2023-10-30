package net.fiap.postech.fastburger.adapters.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.fiap.postech.fastburger.application.domain.enums.StatusOrder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private StatusOrder status;
    private Long clientId;
    private List<OrderItemDTO> orderItens;
}
