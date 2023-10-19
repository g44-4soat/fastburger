package net.fiap.postech.fastburger.modules.infrastructure.exceptionHandler;

public class BusinessException  extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}
