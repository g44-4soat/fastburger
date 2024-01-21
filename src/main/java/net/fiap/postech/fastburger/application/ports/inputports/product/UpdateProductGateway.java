package net.fiap.postech.fastburger.application.ports.inputports.product;

import net.fiap.postech.fastburger.application.domain.Product;

public interface UpdateProductGateway {
    Product update(String id, Product product);
}
