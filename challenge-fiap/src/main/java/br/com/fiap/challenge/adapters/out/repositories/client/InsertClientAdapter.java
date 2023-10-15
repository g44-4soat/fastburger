package br.com.fiap.challenge.adapters.out.repositories.client;

import br.com.fiap.challenge.adapters.out.repositories.entities.client.ClientEntity;
import br.com.fiap.challenge.application.core.domain.client.Client;
import br.com.fiap.challenge.application.ports.out.client.InsertClientOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsertClientAdapter implements InsertClientOutputPort {

    private final ClientRepository clientRepository;

    @Autowired
    public InsertClientAdapter(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client insert(Client client) {
        ClientEntity savedClient = this.clientRepository.save(new ClientEntity(client.getId(), client.getCpf(), client.getNome(), client.getEmail()));
        return new Client(savedClient.getId(), savedClient.getCpf(), savedClient.getNome(), savedClient.getEmail());
    }
}
