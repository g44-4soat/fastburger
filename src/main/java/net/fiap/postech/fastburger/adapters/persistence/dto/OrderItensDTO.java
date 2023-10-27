package net.fiap.postech.fastburger.adapters.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.fiap.postech.fastburger.application.domain.Client;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItensDTO {
    private String orderNumber;
    private LocalDateTime dateTimeCreation;
    private Client client;
    private List<Long> productsId;
}
