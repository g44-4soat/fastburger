package br.com.fiap.challenge.application.core.domain.product.image;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "image")
@Table(name = "image")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
}
