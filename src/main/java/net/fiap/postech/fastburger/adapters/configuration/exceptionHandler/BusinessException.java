package net.fiap.postech.fastburger.adapters.configuration.exceptionHandler;

public class BusinessException  extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}
