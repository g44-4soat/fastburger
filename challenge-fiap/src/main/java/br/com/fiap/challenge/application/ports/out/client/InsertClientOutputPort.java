package br.com.fiap.challenge.application.ports.out.client;

import br.com.fiap.challenge.application.core.domain.client.Client;

public interface InsertClientOutputPort {
    Client insert(Client client);
}
