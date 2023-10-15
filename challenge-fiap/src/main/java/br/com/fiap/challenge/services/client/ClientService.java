package br.com.fiap.challenge.services.client;

import br.com.fiap.challenge.adapters.out.repositories.client.FindClientByCpfAdapter;
import br.com.fiap.challenge.adapters.out.repositories.client.InsertClientAdapter;
import br.com.fiap.challenge.application.core.domain.client.Client;
import br.com.fiap.challenge.adapters.out.repositories.entities.client.ClientDTO;
import br.com.fiap.challenge.adapters.out.repositories.entities.client.ClientMapper;
import br.com.fiap.challenge.domain.exception.business.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final InsertClientAdapter insertClientAdapter;

    private final FindClientByCpfAdapter findClientByCpfAdapter;

    private final ClientMapper clientMapper;

    @Autowired
    public ClientService(InsertClientAdapter insertClientAdapter, ClientMapper clientMapper, FindClientByCpfAdapter findClientByCpfAdapter) {
        this.insertClientAdapter = insertClientAdapter;
        this.clientMapper = clientMapper;
        this.findClientByCpfAdapter = findClientByCpfAdapter;
    }

    public ClientDTO saveClient(ClientDTO client) {
       return this.clientMapper.toClienteDTO(this.insertClientAdapter.insert(this.clientMapper.toEntity(client)));
    }

    public List<ClientDTO> getAllClients() {
        return null;
        // return this.clientMapper.toCollectionClientes(this.clientRepository.findAll());
    }

    public ClientDTO findClientByCpf(String cpf) {
        Client clientByCpf = this.findClientByCpfAdapter.find(cpf);
        if (clientByCpf == null) throw new ClientNotFoundException("Não foram encontrados Clientes com este CPF");
        return this.clientMapper.toClienteDTO(clientByCpf);
    }
}
