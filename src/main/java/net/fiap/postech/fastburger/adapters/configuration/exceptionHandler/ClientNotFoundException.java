package net.fiap.postech.fastburger.adapters.configuration.exceptionHandler;

public class ClientNotFoundException  extends BusinessException{
    public ClientNotFoundException(String message) {
        super(message);
    }
}
