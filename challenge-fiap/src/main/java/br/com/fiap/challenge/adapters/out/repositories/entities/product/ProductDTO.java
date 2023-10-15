package br.com.fiap.challenge.adapters.out.repositories.entities.product;

import br.com.fiap.challenge.adapters.out.repositories.entities.image.ProductImageDTO;
import br.com.fiap.challenge.application.core.domain.enumerations.CategoryEnum;
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
