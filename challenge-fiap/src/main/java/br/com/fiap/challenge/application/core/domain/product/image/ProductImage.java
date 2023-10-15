package br.com.fiap.challenge.application.core.domain.product.image;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class ProductImage {
    private Long id;
    private String url;
}
