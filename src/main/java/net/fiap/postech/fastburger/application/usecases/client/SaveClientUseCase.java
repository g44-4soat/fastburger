package net.fiap.postech.fastburger.application.usecases.client;

import net.fiap.postech.fastburger.application.domain.Client;

public interface SaveClientUseCase {
    Client save(Client client);
}
