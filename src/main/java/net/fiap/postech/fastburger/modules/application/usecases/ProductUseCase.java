package net.fiap.postech.fastburger.modules.application.usecases;

import net.fiap.postech.fastburger.modules.domain.enums.CategoryEnum;

import java.util.List;

public interface ProductUseCase {
    Object insert(Object produto);

    Object update(Long id, Object product);

    List<Object> findByCategory(CategoryEnum category);
}
