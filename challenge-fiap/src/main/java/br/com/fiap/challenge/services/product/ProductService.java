package br.com.fiap.challenge.services.product;

import br.com.fiap.challenge.domain.enumerations.CategoryEnum;
import br.com.fiap.challenge.domain.product.Product;
import br.com.fiap.challenge.repositories.product.ProductRepository;
import jakarta.servlet.http.PushBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) {
        return this.productRepository.save(product);
    }

    public List<Product> getProductByCategory(CategoryEnum categoryEnum) {
        return this.productRepository.findByCategoryEnum(categoryEnum);
    }

    @Transactional
    public ResponseEntity<Product> updateProduct(Product product) {
        Product oldProduct = this.productRepository.findById(product.getId()).get();
        if (oldProduct != null) return ResponseEntity.ok(this.productRepository.save(product));
        return ResponseEntity.notFound().build();
    }

    public void deleteProduct(Long productId) {
        Product oldProduct = this.productRepository.findById(productId).get();
        if (oldProduct != null) this.productRepository.delete(oldProduct);
        else throw new RuntimeException("Product not found");
    }
}
