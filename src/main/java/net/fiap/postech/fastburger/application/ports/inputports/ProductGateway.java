package net.fiap.postech.fastburger.application.ports.inputports;

import net.fiap.postech.fastburger.application.usecases.product.*;

import java.util.List;

public class ProductGateway implements SaveProductUseCase, UpdateProductUseCase, FindAllProductUseCase, DeleteProductUseCase, FindProductByCategoryUseCase, FindProductByIdUseCase {
    @Override
    public Void delete(String id) {
        return null;
    }

    @Override
    public List<Object> find() {
        return null;
    }

    @Override
    public Object save(Object product) {
        return null;
    }

    @Override
    public Object update(String id, Object product) {
        return null;
    }
}
