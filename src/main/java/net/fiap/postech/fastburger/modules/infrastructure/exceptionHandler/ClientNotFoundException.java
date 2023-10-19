package net.fiap.postech.fastburger.modules.infrastructure.exceptionHandler;

public class ClientNotFoundException  extends BusinessException{
    public ClientNotFoundException(String message) {
        super(message);
    }
}
