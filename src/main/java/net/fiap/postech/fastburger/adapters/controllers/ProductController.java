package net.fiap.postech.fastburger.adapters.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import net.fiap.postech.fastburger.adapters.persistence.ProductDTO;
import net.fiap.postech.fastburger.adapters.persistence.mapper.ProductMapper;
import net.fiap.postech.fastburger.application.domain.Product;
import net.fiap.postech.fastburger.application.ports.inputports.product.SaveProductGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/product")
@Tag(name = "Product Controller Rest")
public class ProductController {

    private final SaveProductGateway saveProductGateway;
    private final ProductMapper productMapper;

    @Autowired
    public ProductController(SaveProductGateway saveProductGateway, ProductMapper productMapper) {
        this.saveProductGateway = saveProductGateway;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody @Valid ProductDTO productDTO) {
        var productEntity = this.productMapper.toEntity(productDTO);
        Product saved = this.saveProductGateway.save(this.productMapper.toDomain(productEntity));
        return ResponseEntity.ok(this.productMapper.domainToDTO(saved));
    }
}
