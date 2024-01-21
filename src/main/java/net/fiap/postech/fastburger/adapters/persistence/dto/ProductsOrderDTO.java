package net.fiap.postech.fastburger.adapters.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductsOrderDTO {
    private Long productId;
    private Long quantityOfProducts;
}
