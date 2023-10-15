package br.com.fiap.challenge.application.core.domain.product.image;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ProductImageMapper {

    private ModelMapper modelMapper;

    public ProductImageDTO toProductImageDTO(ProductImage productImage) {
        return modelMapper.map(productImage, ProductImageDTO.class);
    }

    public ProductImage toEntity(ProductImageDTO productImageDTO) {
        return modelMapper.map(productImageDTO, ProductImage.class);
    }

    public List<ProductImageDTO> toCollectionProductImage(List<ProductImage> productImageList) {
        return productImageList.stream()
                .map(this::toProductImageDTO)
                .collect(Collectors.toList());
    }
}
