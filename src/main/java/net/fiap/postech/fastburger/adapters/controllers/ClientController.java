package net.fiap.postech.fastburger.adapters.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.fiap.postech.fastburger.adapters.persistence.ClientDTO;
import net.fiap.postech.fastburger.adapters.persistence.repositories.ClientRepositoryImpl;
import net.fiap.postech.fastburger.application.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/client")
@Tag(name = "Client Controller Rest")
public class ClientController {

    private ClientRepositoryImpl clientRepository;

    @PostMapping
    public void save(@RequestBody ClientDTO clientDTO) {
        Client saved = this.clientRepository.save(
                new Client(null, clientDTO.getNome(), clientDTO.getCpf(), clientDTO.getEmail())
        );
    }
}
