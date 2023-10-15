package br.com.fiap.challenge.application.core.domain.product;

import br.com.fiap.challenge.application.core.domain.enumerations.CategoryEnum;
import br.com.fiap.challenge.application.core.domain.product.image.ProductImageDTO;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class ProductDTO {

    @NotBlank
    private String name;

    @NotBlank
    private CategoryEnum categoryEnum;

    @NotBlank
    private Double price;

    @NotBlank
    private String description;
    @NotBlank
    private List<ProductImageDTO> images;
}
