package br.com.fiap.challenge.application.core.domain.product;

import br.com.fiap.challenge.domain.enumerations.CategoryEnum;
import br.com.fiap.challenge.application.core.domain.product.image.ProductImage;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Product {

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
    private List<ProductImage> images;

}
