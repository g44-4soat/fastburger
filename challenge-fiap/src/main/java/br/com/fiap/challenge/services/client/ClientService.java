package br.com.fiap.challenge.services.client;

import br.com.fiap.challenge.domain.client.Client;
import br.com.fiap.challenge.domain.client.ClientDTO;
import br.com.fiap.challenge.repositories.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client saveClient(Client client) {
        return this.clientRepository.save(client);
    }

    public List<Client> getAllClients(){
        return this.clientRepository.findAll();
    }

    public Client findClientByCpf(String cpf){
        return this.clientRepository.findClientByCpf(cpf);
    }
}
