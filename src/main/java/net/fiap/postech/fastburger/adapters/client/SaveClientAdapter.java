package net.fiap.postech.fastburger.adapters.client;

import net.fiap.postech.fastburger.adapters.persistence.mapper.ClientMapper;
import net.fiap.postech.fastburger.adapters.persistence.repositories.ClientRepository;
import net.fiap.postech.fastburger.application.domain.Client;
import net.fiap.postech.fastburger.application.ports.inputports.client.SaveClientGateway;
import net.fiap.postech.fastburger.application.ports.outputports.client.SaveClientOutPutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveClientAdapter implements SaveClientOutPutPort {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final ClientService clientService;

    @Autowired
    public SaveClientAdapter(ClientRepository clientRepository, ClientMapper clientMapper, ClientService clientService) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.clientService = clientService;
    }
    @Override
    public Client save(Client client) {
        var clientEntity = this.clientMapper.toEntity(client);
        var savedClient = this.clientRepository.save(clientEntity);

        if (client.getNome() != null && client.getEmail() != null && client.getCpf() != null)
            this.clientService.saveClientOnCognito(client);
        return this.clientMapper.toDomain(savedClient);
    }
}
