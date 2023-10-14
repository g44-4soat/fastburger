package br.com.fiap.challenge.domain.product;

import br.com.fiap.challenge.domain.enumerations.CategoryEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
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
    private String images;

}
