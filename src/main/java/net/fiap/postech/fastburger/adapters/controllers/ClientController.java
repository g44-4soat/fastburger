package net.fiap.postech.fastburger.adapters.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import net.fiap.postech.fastburger.adapters.configuration.exceptionHandler.HandlerBodyException;
import net.fiap.postech.fastburger.adapters.persistence.dto.ClientDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.ClientResponseDTO;
import net.fiap.postech.fastburger.adapters.persistence.mapper.ClientMapper;
import net.fiap.postech.fastburger.application.domain.Client;
import net.fiap.postech.fastburger.application.ports.inputports.client.FindClientByCpfGateway;
import net.fiap.postech.fastburger.application.ports.inputports.client.SaveClientGateway;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/client")
@Tag(name = "Client Controller Rest")
public class ClientController {


    private SaveClientGateway saveClientGateway;
    private FindClientByCpfGateway findClientByCpfGateway;
    private ClientMapper clientMapper;

    @Autowired
    public ClientController(SaveClientGateway saveClientGateway, FindClientByCpfGateway findClientByCpfGateway, ClientMapper clientMapper) {
        this.saveClientGateway = saveClientGateway;
        this.findClientByCpfGateway = findClientByCpfGateway;
        this.clientMapper = clientMapper;
    }

    @GetMapping("/{cpf}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(description = "Busca um cliente pelo CPF", method = "GET")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Não foi encontrado cliente vinculado ao CPF", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = NotFound.class))
                    })
            }
    )
    public ResponseEntity<ClientResponseDTO> findClientByCpf(@PathVariable("cpf") String cpf) {
        var client = this.findClientByCpfGateway.find(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(this.clientMapper.entityDomainToResponseDTO(client));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(description = "Realiza o cadastro de um novo Cliente", method = "POST")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))
                    }),
                    @ApiResponse(responseCode = "400", description = "Cliente não estruturado corretamente", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = HandlerBodyException.class))
                    })
            }
    )
    public ResponseEntity<ClientResponseDTO> save(@RequestBody @Valid ClientDTO clientDTO) {
        Client saved = this.saveClientGateway.save(clientMapper.dtoToEntityDomain(clientDTO));

        return Optional.of(this.clientMapper.entityDomainToResponseDTO(saved))
                .map(client -> ResponseEntity.status(HttpStatus.CREATED).body(client))
                .orElse(ResponseEntity
                        .badRequest().build());
    }
}
