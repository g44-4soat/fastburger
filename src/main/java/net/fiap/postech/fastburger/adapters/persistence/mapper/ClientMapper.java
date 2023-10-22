package net.fiap.postech.fastburger.adapters.persistence.mapper;

import net.fiap.postech.fastburger.adapters.persistence.ClientDTO;
import net.fiap.postech.fastburger.adapters.persistence.entities.ClientEntity;
import net.fiap.postech.fastburger.application.domain.Client;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

public class ClientMapper {
    private ModelMapper modelMapper;

    public ClientEntity toEntity(Client client) {
        return modelMapper.map(client, ClientEntity.class);
    }

    public ClientEntity toEntity(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, ClientEntity.class);
    }

    public Client toDomain(ClientEntity clientEntity) {
        return modelMapper.map(clientEntity, Client.class);
    }

    public ClientDTO toDTO(ClientEntity clientEntity) {
        return modelMapper.map(clientEntity, ClientDTO.class);
    }
}
