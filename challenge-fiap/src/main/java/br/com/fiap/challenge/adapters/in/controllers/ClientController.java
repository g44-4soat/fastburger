package br.com.fiap.challenge.adapters.in.controllers;

import br.com.fiap.challenge.adapters.out.repositories.entities.client.ClientDTO;
import br.com.fiap.challenge.services.client.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/client")
@Tag(name = "Client controller")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClientDTO> saveClient(@RequestBody @Valid ClientDTO client) {
        return ResponseEntity.ok(this.clientService.saveClient(client));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return Optional.of(this.clientService.getAllClients()).map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/{cpf}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ClientDTO> findClientByCpf(@PathVariable("cpf") String cpf) {
        return Optional.of(this.clientService.findClientByCpf(cpf)).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
