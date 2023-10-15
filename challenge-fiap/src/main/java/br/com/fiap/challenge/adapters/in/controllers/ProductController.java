package br.com.fiap.challenge.adapters.in.controllers;

import br.com.fiap.challenge.domain.enumerations.CategoryEnum;
import br.com.fiap.challenge.application.core.domain.product.Product;
import br.com.fiap.challenge.services.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@Tag(name = "Product controller")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Salvar novos produtos", method = "POST")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Inclusão realizada com sucesso", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
                    }),
                    @ApiResponse(responseCode = "500", description = "erro interno do servidor", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = InternalError.class))
                    })
            }
    )
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return ResponseEntity.ok(this.productService.saveProduct(product));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(description = "Busca produto por categoria", method = "GET")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "202", description = "busca realizada com sucesso", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Product.class)))
                    }),
                    @ApiResponse(responseCode = "404", description = "Produtos não encontrados para esta categoria", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = NotFound.class))
                    }),
                    @ApiResponse(responseCode = "500", description = "erro interno do servidor", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = InternalError.class))
                    })
            }
    )
    public ResponseEntity<List<Product>> findProductByCategory(CategoryEnum categoryEnum) {
        List<Product> productByCategory = this.productService.getProductByCategory(categoryEnum);
        if (!productByCategory.isEmpty()) return ResponseEntity.ok(productByCategory);
        else return ResponseEntity.notFound().build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return this.productService.updateProduct(product);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("productId") Long productId) {
        this.productService.deleteProduct(productId);
    }
}
