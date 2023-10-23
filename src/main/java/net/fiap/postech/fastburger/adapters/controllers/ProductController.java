package net.fiap.postech.fastburger.adapters.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import net.fiap.postech.fastburger.adapters.configuration.exceptionHandler.BusinessException;
import net.fiap.postech.fastburger.adapters.persistence.ProductDTO;
import net.fiap.postech.fastburger.adapters.persistence.ProductResponseDTO;
import net.fiap.postech.fastburger.adapters.persistence.mapper.ProductMapper;
import net.fiap.postech.fastburger.application.domain.Product;
import net.fiap.postech.fastburger.application.domain.enums.CategoryEnum;
import net.fiap.postech.fastburger.application.ports.inputports.product.FindProductByCategoryGateway;
import net.fiap.postech.fastburger.application.ports.inputports.product.SaveProductGateway;
import net.fiap.postech.fastburger.application.ports.inputports.product.UpdateProductGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/product")
@Tag(name = "Product Controller Rest")
public class ProductController {

    private final SaveProductGateway saveProductGateway;
    private final FindProductByCategoryGateway findProductByCategoryGateway;

    private final UpdateProductGateway updateProductGateway;
    private final ProductMapper productMapper;

    @Autowired
    public ProductController(SaveProductGateway saveProductGateway,
                             ProductMapper productMapper,
                             FindProductByCategoryGateway findProductByCategoryGateway,
                             UpdateProductGateway updateProductGateway) {
        this.saveProductGateway = saveProductGateway;
        this.productMapper = productMapper;
        this.updateProductGateway = updateProductGateway;
        this.findProductByCategoryGateway = findProductByCategoryGateway;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody @Valid ProductDTO productDTO) {
        var productEntity = this.productMapper.toEntity(productDTO);
        Product saved = this.saveProductGateway.save(this.productMapper.toDomain(productEntity));
        return ResponseEntity.ok(this.productMapper.domainToDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findProductByCategory(CategoryEnum categoryEnum) {
        var productEntity = this.findProductByCategoryGateway.find(categoryEnum);
        List<ProductResponseDTO> productDTOList = new ArrayList<>();
        productEntity.forEach(product -> productDTOList.add(this.productMapper.domainToDTOResponse(product)));

        return ResponseEntity.ok(productDTOList);
    }

    @PutMapping("/{sku}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathParam("sku") Long sku, @Valid @RequestBody ProductDTO productDTO) {
        var productUpdated = this.updateProductGateway.update(sku.toString(), this.productMapper.dtoToDomain(productDTO));
        return ResponseEntity.ok(this.productMapper.domainToDTOResponse(productUpdated));
    }
}
