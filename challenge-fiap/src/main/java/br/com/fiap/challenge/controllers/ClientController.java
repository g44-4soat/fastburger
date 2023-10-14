package br.com.fiap.challenge.controllers;

import br.com.fiap.challenge.domain.client.Client;
import br.com.fiap.challenge.services.client.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> saveClient(@RequestBody @Valid Client client) {
        return ResponseEntity.ok(this.clientService.saveClient(client));
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(this.clientService.getAllClients());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Client> findClientByCpf(@PathVariable("cpf") String cpf) {
        Client clientByCpf = this.clientService.findClientByCpf(cpf);
        if (clientByCpf != null) return ResponseEntity.ok(clientByCpf);
        else return ResponseEntity.notFound().build();
    }
}
