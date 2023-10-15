package br.com.fiap.challenge.adapters.out.repositories.entities.product;

import br.com.fiap.challenge.application.core.domain.product.Product;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ProductMapper {
    private ModelMapper modelMapper;

    public ProductDTO toProductDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    public Product toEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }

    public List<ProductDTO> toCollectionProductImage(List<Product> products) {
        return products.stream()
                .map(this::toProductDTO)
                .collect(Collectors.toList());
    }
}
