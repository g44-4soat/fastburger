package br.com.fiap.challenge.domain.client;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ClientMapper {
    private ModelMapper modelMapper;

    public ClientDTO toClienteDTO(Client cliente) {
        return modelMapper.map(cliente, ClientDTO.class);
    }

    public Client toEntity(ClientDTO clienteDTO) {
        return modelMapper.map(clienteDTO, Client.class);
    }

    public List<ClientDTO> toCollectionClientes(List<Client> clientes) {
        return clientes.stream()
                .map(this::toClienteDTO)
                .collect(Collectors.toList());
    }
}
