package net.fiap.postech.fastburger.application.ports.outputports.product;

import net.fiap.postech.fastburger.application.domain.Product;

public interface SaveProductOutPutPort {
    Product save(Product product);
}
