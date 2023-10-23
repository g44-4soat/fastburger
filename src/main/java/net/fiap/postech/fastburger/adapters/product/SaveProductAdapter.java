package net.fiap.postech.fastburger.adapters.product;

import net.fiap.postech.fastburger.adapters.configuration.exceptionHandler.BusinessException;
import net.fiap.postech.fastburger.adapters.persistence.mapper.ProductMapper;
import net.fiap.postech.fastburger.adapters.persistence.repositories.ProductRepository;
import net.fiap.postech.fastburger.application.domain.Product;
import net.fiap.postech.fastburger.application.ports.inputports.product.SaveProductGateway;
import net.fiap.postech.fastburger.application.ports.outputports.product.SaveProductOutPutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveProductAdapter implements SaveProductOutPutPort {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public SaveProductAdapter(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product save(Product product) {
        var productSaved = this.productRepository.save(this.productMapper.domainToEntity(product));
        return this.productMapper.toDomain(productSaved);
    }
}
