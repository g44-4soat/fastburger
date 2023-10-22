package net.fiap.postech.fastburger.adapters;

import net.fiap.postech.fastburger.adapters.configuration.exceptionHandler.BusinessException;
import net.fiap.postech.fastburger.adapters.persistence.mapper.ProductMapper;
import net.fiap.postech.fastburger.adapters.persistence.repositories.ProductRepository;
import net.fiap.postech.fastburger.application.domain.Product;
import net.fiap.postech.fastburger.application.ports.inputports.product.SaveProductGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveProductAdapter implements SaveProductGateway {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public SaveProductAdapter(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product save(Product product) {
        if (product.getPrice() <= 0) {
            throw new BusinessException("O valor do produto não pode ser igual ou menor que zero!");
        }
        var productSaved = this.productRepository.save(this.productMapper.domainToEntity(product));
        return this.productMapper.toDomain(productSaved);
    }
}
