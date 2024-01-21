package net.fiap.postech.fastburger.adapters.persistence.mapper;

import net.fiap.postech.fastburger.adapters.persistence.dto.ProductDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.ProductResponseDTO;
import net.fiap.postech.fastburger.adapters.persistence.entities.ProductEntity;
import net.fiap.postech.fastburger.application.domain.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ProductEntity toEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, ProductEntity.class);
    }

    public ProductDTO toDTO(ProductEntity productEntity) {
        return modelMapper.map(productEntity, ProductDTO.class);
    }

    public Product toDomain(ProductEntity productEntity) {
        return modelMapper.map(productEntity, Product.class);
    }

    public Product dtoToDomain(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }

    public ProductEntity domainToEntity(Product product) {
        return modelMapper.map(product, ProductEntity.class);
    }

    public ProductDTO domainToDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    public ProductResponseDTO domainToDTOResponse(Product product) {
        return modelMapper.map(product, ProductResponseDTO.class);
    }

}
