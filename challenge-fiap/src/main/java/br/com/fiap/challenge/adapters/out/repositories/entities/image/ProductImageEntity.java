package br.com.fiap.challenge.adapters.out.repositories.entities.image;

import jakarta.persistence.*;

@Entity(name = "image")
@Table(name = "image")
public class ProductImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
}
