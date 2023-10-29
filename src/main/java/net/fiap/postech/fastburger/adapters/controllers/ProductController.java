package net.fiap.postech.fastburger.adapters.controllers;

import com.fasterxml.jackson.databind.annotation.NoClass;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import net.fiap.postech.fastburger.adapters.configuration.exceptionHandler.HandlerBodyException;
import net.fiap.postech.fastburger.adapters.persistence.dto.ProductDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.ProductResponseDTO;
import net.fiap.postech.fastburger.adapters.persistence.mapper.ProductMapper;
import net.fiap.postech.fastburger.application.domain.Client;
import net.fiap.postech.fastburger.application.domain.Product;
import net.fiap.postech.fastburger.application.domain.enums.CategoryEnum;
import net.fiap.postech.fastburger.application.ports.inputports.product.DeleteProductGateway;
import net.fiap.postech.fastburger.application.ports.inputports.product.FindProductByCategoryGateway;
import net.fiap.postech.fastburger.application.ports.inputports.product.SaveProductGateway;
import net.fiap.postech.fastburger.application.ports.inputports.product.UpdateProductGateway;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@Tag(name = "Product Controller Rest")
public class ProductController {

    private final SaveProductGateway saveProductGateway;
    private final FindProductByCategoryGateway findProductByCategoryGateway;

    private final DeleteProductGateway deleteProductGateway;
    private final UpdateProductGateway updateProductGateway;
    private final ProductMapper productMapper;

    @Autowired
    public ProductController(SaveProductGateway saveProductGateway,
                             ProductMapper productMapper,
                             FindProductByCategoryGateway findProductByCategoryGateway,
                             UpdateProductGateway updateProductGateway,
                             DeleteProductGateway deleteProductGateway) {
        this.saveProductGateway = saveProductGateway;
        this.productMapper = productMapper;
        this.updateProductGateway = updateProductGateway;
        this.findProductByCategoryGateway = findProductByCategoryGateway;
        this.deleteProductGateway = deleteProductGateway;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(description = "Realiza o cadastro de um novo Produto", method = "POST")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Produto não estruturado corretamente", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerBodyException.class))
                    })
            }
    )
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody @Valid ProductDTO productDTO) {
        var productEntity = this.productMapper.toEntity(productDTO);
        Product saved = this.saveProductGateway.save(this.productMapper.toDomain(productEntity));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productMapper.domainToDTO(saved));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Busca produtos por categoria", method = "GET")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "busca realizada com sucesso", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Product.class)))
                    }),
                    @ApiResponse(responseCode = "404", description = "Produtos não encontrados para esta categoria", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = NotFound.class))
                    })
            }
    )
    public ResponseEntity<List<ProductResponseDTO>> findProductByCategory(CategoryEnum categoryEnum) {
        var productEntity = this.findProductByCategoryGateway.find(categoryEnum);
        List<ProductResponseDTO> productDTOList = new ArrayList<>();
        productEntity.forEach(product -> productDTOList.add(this.productMapper.domainToDTOResponse(product)));
        return ResponseEntity.status(HttpStatus.OK).body(productDTOList);
    }

    @PutMapping("/{sku}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Atualiza produto por SKU", method = "GET")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = NotFound.class))
                    })
            }
    )
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathParam("sku") Long sku, @Valid @RequestBody ProductDTO productDTO) {
        var productUpdated = this.updateProductGateway.update(sku.toString(), this.productMapper.dtoToDomain(productDTO));
        return ResponseEntity.status(HttpStatus.OK).body(this.productMapper.domainToDTOResponse(productUpdated));
    }

    @DeleteMapping("/{sku}")
    @PutMapping("/{sku}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Atualiza produto por SKU", method = "GET")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Deleção realizada com sucesso", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = NoClass.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = NotFound.class))
                    })
            }
    )
    public ResponseEntity<Void> deleteProductBySKU(@PathVariable("sku") Long sku) {
        this.deleteProductGateway.delete(sku.toString());
        return ResponseEntity.noContent().build();
    }
}
