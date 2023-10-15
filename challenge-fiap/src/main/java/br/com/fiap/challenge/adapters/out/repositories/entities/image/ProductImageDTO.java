package br.com.fiap.challenge.adapters.out.repositories.entities.image;

import jakarta.validation.constraints.NotBlank;

public class ProductImageDTO {
    @NotBlank
    private String image;
}
