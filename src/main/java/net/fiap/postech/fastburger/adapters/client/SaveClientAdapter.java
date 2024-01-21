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

    @Autowired
    public SaveClientAdapter(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public Client save(Client client) {
        var clientEntity = this.clientMapper.toEntity(client);
        var savedClient = this.clientRepository.save(clientEntity);
        return this.clientMapper.toDomain(savedClient);
    }
}
