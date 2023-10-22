package net.fiap.postech.fastburger.adapters.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import net.fiap.postech.fastburger.adapters.persistence.ClientDTO;
import net.fiap.postech.fastburger.adapters.persistence.mapper.ClientMapper;
import net.fiap.postech.fastburger.application.domain.Client;
import net.fiap.postech.fastburger.application.ports.inputports.client.FindClientByCpfGateway;
import net.fiap.postech.fastburger.application.ports.inputports.client.SaveClientGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/client")
@Tag(name = "Client Controller Rest")
public class ClientController {


    private  SaveClientGateway saveClientGateway;
    private  FindClientByCpfGateway findClientByCpfGateway;
    private  ClientMapper clientMapper;

    @Autowired
    public ClientController(SaveClientGateway saveClientGateway, FindClientByCpfGateway findClientByCpfGateway, ClientMapper clientMapper) {
        this.saveClientGateway = saveClientGateway;
        this.findClientByCpfGateway = findClientByCpfGateway;
        this.clientMapper = clientMapper;
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClientDTO> findClientByCpf(@PathVariable("cpf") String cpf) {
        var client = this.findClientByCpfGateway.find(cpf);
        return ResponseEntity.ok(this.clientMapper.entityDomainToDTO(client));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> save(@RequestBody @Valid ClientDTO clientDTO) {
        Client saved = this.saveClientGateway.save(clientMapper.dtoToEntityDomain(clientDTO));
        return Optional.of(this.clientMapper.entityDomainToDTO(saved))
                .map(client -> ResponseEntity.ok(client))
                .orElse(ResponseEntity
                        .badRequest().build());
    }
}
