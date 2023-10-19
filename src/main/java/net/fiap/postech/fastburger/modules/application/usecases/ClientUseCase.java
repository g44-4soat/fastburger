package net.fiap.postech.fastburger.modules.application.usecases;

public interface ClientUseCase {
    Object insert(Object client);

    Object findByCpf(String cpf);
}
