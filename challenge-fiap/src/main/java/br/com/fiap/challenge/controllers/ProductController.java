package br.com.fiap.challenge.controllers;

import br.com.fiap.challenge.domain.enumerations.CategoryEnum;
import br.com.fiap.challenge.domain.product.Product;
import br.com.fiap.challenge.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(Product product) {
        return ResponseEntity.ok(this.productService.saveProduct(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> findProductByCategory(CategoryEnum categoryEnum) {
        List<Product> productByCategory = this.productService.getProductByCategory(categoryEnum);
        if (!productByCategory.isEmpty()) return ResponseEntity.ok(productByCategory);
        else return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return this.productService.updateProduct(product);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId) {
        this.productService.deleteProduct(productId);
    }
}
