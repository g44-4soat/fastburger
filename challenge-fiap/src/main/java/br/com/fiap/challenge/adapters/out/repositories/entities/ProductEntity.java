package br.com.fiap.challenge.adapters.out.repositories.entities;

import br.com.fiap.challenge.application.core.domain.enumerations.CategoryEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryEnum categoryEnum;
    private Double price;
    private String description;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_image",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "image_id")}
    )
    private List<ProductImageEntity> images;
}
