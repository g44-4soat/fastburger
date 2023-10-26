package net.fiap.postech.fastburger.adapters.persistence.mapper;

import net.fiap.postech.fastburger.adapters.persistence.dto.ClientDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.ClientResponseDTO;
import net.fiap.postech.fastburger.adapters.persistence.entities.ClientEntity;
import net.fiap.postech.fastburger.application.domain.Client;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ClientEntity toEntity(Client client) {
        return modelMapper.map(client, ClientEntity.class);
    }

    public ClientDTO entityDomainToDTO(Client client) {
        return modelMapper.map(client, ClientDTO.class);
    }

    public ClientResponseDTO entityDomainToResponseDTO(Client client) {
        return modelMapper.map(client, ClientResponseDTO.class);
    }

    public ClientEntity dtoToEntity(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, ClientEntity.class);
    }

    public Client dtoToEntityDomain(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, Client.class);
    }

    public Client toDomain(ClientEntity clientEntity) {
        return modelMapper.map(clientEntity, Client.class);
    }

    public ClientDTO toDTO(ClientEntity clientEntity) {
        return modelMapper.map(clientEntity, ClientDTO.class);
    }

    public ClientResponseDTO toResponseDTO(ClientEntity clientEntity) {
        return modelMapper.map(clientEntity, ClientResponseDTO.class);
    }
}
