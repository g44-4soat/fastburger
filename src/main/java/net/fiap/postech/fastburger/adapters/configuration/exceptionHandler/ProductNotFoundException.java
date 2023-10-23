package net.fiap.postech.fastburger.adapters.configuration.exceptionHandler;

public class ProductNotFoundException extends BusinessException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
