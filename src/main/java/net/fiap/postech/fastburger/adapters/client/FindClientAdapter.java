package net.fiap.postech.fastburger.adapters.client;

import net.fiap.postech.fastburger.adapters.configuration.exceptionHandler.ClientNotFoundException;
import net.fiap.postech.fastburger.adapters.persistence.mapper.ClientMapper;
import net.fiap.postech.fastburger.adapters.persistence.repositories.ClientRepository;
import net.fiap.postech.fastburger.application.domain.Client;
import net.fiap.postech.fastburger.application.ports.inputports.client.FindClientByCpfGateway;
import net.fiap.postech.fastburger.application.ports.outputports.client.FindClientByCpfOutPutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindClientAdapter implements FindClientByCpfOutPutPort {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Autowired
    public FindClientAdapter(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public Client find(String cpf) {
        var client = this.clientRepository.findClientEntityByCpf(cpf);
        return this.clientMapper.toDomain(client.orElseThrow(() -> new ClientNotFoundException("Client not found.")));
    }
}
