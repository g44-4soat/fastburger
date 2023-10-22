package net.fiap.postech.fastburger.application.ports.outputports.product;

import net.fiap.postech.fastburger.application.domain.Product;
import net.fiap.postech.fastburger.application.domain.enums.CategoryEnum;

import java.util.List;

public interface FindProductByCategoryOutPutPort {
    List<Product> find(CategoryEnum categoryEnum);
}
