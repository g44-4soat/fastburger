package br.com.fiap.challenge.application.core.domain.client;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ClientMapper {

    private ModelMapper modelMapper;

    public ClientDTO toClienteDTO(Client client) {
        return modelMapper.map(client, ClientDTO.class);
    }

    public Client toEntity(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, Client.class);
    }

    public List<ClientDTO> toCollectionClientes(List<Client> clients) {
        return clients.stream()
                .map(this::toClienteDTO)
                .collect(Collectors.toList());
    }
}
