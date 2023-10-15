package br.com.fiap.challenge.application.core.usecase.client;

import br.com.fiap.challenge.application.core.domain.client.Client;
import br.com.fiap.challenge.application.ports.out.client.InsertClientOutputPort;

public class InsertClientUseCase {
    private final InsertClientOutputPort insertClientOutputPort;

    public InsertClientUseCase(InsertClientOutputPort insertClientOutputPort) {
        this.insertClientOutputPort = insertClientOutputPort;
    }

    public Client insert(Client client) {
        return this.insertClientOutputPort.insert(client);
    }
}
