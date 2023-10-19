package net.fiap.postech.fastburger.modules.domain.entities;

import lombok.Data;
import net.fiap.postech.fastburger.modules.domain.enums.CategoryEnum;

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
