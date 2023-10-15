package br.com.fiap.challenge.application.core.domain.product.image;

import jakarta.validation.constraints.NotBlank;

public class ProductImageDTO {
    @NotBlank
    private String image;
}
