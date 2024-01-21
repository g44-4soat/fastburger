package net.fiap.postech.fastburger.application.ports.inputports.product;

import net.fiap.postech.fastburger.application.domain.Product;

public interface SaveProductGateway{
    Product save(Product product);
}
