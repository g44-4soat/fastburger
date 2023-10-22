package net.fiap.postech.fastburger.application.usecases.client;

import net.fiap.postech.fastburger.application.domain.Client;

public interface FindClientByCpfUseCase {
    Client find(String cpf);
}
