package br.com.fiap.challenge.application.core.domain.product;

import br.com.fiap.challenge.application.core.domain.enumerations.CategoryEnum;
import br.com.fiap.challenge.application.core.domain.product.image.ProductImage;
import lombok.Data;

import java.util.List;

@Data
public class Product {

    private Long id;
    private String name;
    private CategoryEnum categoryEnum;
    private Double price;
    private String description;
    private List<ProductImage> images;

}
