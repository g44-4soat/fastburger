package net.fiap.postech.fastburger.adapters.persistence.repositories;

import jakarta.transaction.Transactional;
import net.fiap.postech.fastburger.adapters.persistence.entities.ClientEntity;
import net.fiap.postech.fastburger.application.domain.Client;
import net.fiap.postech.fastburger.application.ports.outputports.client.FindClientByCpfOutPutPort;
import net.fiap.postech.fastburger.application.ports.outputports.client.SaveClientOutPutPort;

public class ClientRepositoryImpl implements FindClientByCpfOutPutPort, SaveClientOutPutPort {

    private final ClientRepository clientRepository;

    public ClientRepositoryImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client find(String cpf) {
        return null;
    }

    @Override
    @Transactional
    public Client save(Client client) {
        ClientEntity saved = this.clientRepository.save(
                new ClientEntity(client.getId(), client.getNome(), client.getCpf(), client.getEmail()));
        return new Client(saved.getId(), saved.getNome(), saved.getCpf(), saved.getEmail());
    }
}
