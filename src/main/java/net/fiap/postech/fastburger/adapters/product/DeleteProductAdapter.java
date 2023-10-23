package net.fiap.postech.fastburger.adapters.product;

import net.fiap.postech.fastburger.adapters.configuration.exceptionHandler.BusinessException;
import net.fiap.postech.fastburger.adapters.configuration.exceptionHandler.ProductNotFoundException;
import net.fiap.postech.fastburger.adapters.persistence.mapper.ProductMapper;
import net.fiap.postech.fastburger.adapters.persistence.repositories.ProductRepository;
import net.fiap.postech.fastburger.application.ports.inputports.product.DeleteProductGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteProductAdapter implements DeleteProductGateway {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public DeleteProductAdapter(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Void delete(String sku) {
        var prodcutToDelete = this.productRepository.findById(Long.parseLong(sku));
        if (!prodcutToDelete.isPresent())
            throw new ProductNotFoundException("Produto não encontrado.");
        this.productRepository.deleteById(Long.parseLong(sku));
        return null;
    }
}
