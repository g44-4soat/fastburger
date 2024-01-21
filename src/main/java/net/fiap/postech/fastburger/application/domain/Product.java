package net.fiap.postech.fastburger.application.domain;
import lombok.*;
import net.fiap.postech.fastburger.application.domain.enums.CategoryEnum;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long SKU;
    private String name;
    private CategoryEnum categoryEnum;
    private Double price;
    private String description;
    private List<ProductImage> images;
}
