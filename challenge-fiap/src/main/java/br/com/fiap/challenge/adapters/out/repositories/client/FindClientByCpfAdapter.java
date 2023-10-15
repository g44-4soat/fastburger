package br.com.fiap.challenge.adapters.out.repositories.client;

import br.com.fiap.challenge.adapters.out.repositories.entities.ClientEntity;
import br.com.fiap.challenge.application.core.domain.client.Client;
import br.com.fiap.challenge.application.ports.out.client.FindClientByCpfOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindClientByCpfAdapter implements FindClientByCpfOutputPort {

    private final ClientRepository clientRepository;

    @Autowired
    public FindClientByCpfAdapter(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client find(String cpf) {
        ClientEntity clientByCpf = this.clientRepository.findClientByCpf(cpf);
        return new Client(clientByCpf.getId(), clientByCpf.getCpf(), clientByCpf.getNome(), clientByCpf.getEmail());
    }
}
