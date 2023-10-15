package br.com.fiap.challenge.services.client;

import br.com.fiap.challenge.application.core.domain.client.Client;
import br.com.fiap.challenge.application.core.domain.client.ClientDTO;
import br.com.fiap.challenge.application.core.domain.client.ClientMapper;
import br.com.fiap.challenge.domain.exception.business.ClientNotFoundException;
import br.com.fiap.challenge.adapters.out.repositories.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public ClientDTO saveClient(ClientDTO client) {
        return this.clientMapper.toClienteDTO(this.clientRepository.save(this.clientMapper.toEntity(client)));
    }

    public List<ClientDTO> getAllClients() {
        return this.clientMapper.toCollectionClientes(this.clientRepository.findAll());
    }

    public ClientDTO findClientByCpf(String cpf) {
        Client clientByCpf = this.clientRepository.findClientByCpf(cpf);
        if (clientByCpf == null) throw new ClientNotFoundException("Não foram encontrados Clientes com este CPF");
        return this.clientMapper.toClienteDTO(clientByCpf);
    }
}
