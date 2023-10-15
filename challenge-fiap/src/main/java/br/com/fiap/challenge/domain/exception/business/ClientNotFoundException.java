package br.com.fiap.challenge.domain.exception.business;

public class ClientNotFoundException extends BusinessException{
    public ClientNotFoundException(String message) {
        super(message);
    }
}
