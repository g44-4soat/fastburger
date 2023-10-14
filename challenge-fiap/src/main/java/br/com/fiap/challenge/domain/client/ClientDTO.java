package br.com.fiap.challenge.domain.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.RepresentationModel;

@Data
public class ClientDTO extends RepresentationModel<ClientDTO> {

    @NotBlank
    @CPF
    @Size(min = 11, max = 15)
    private String cpf;

    @NotBlank
    @Size(min = 3, max = 50)
    private String nome;

    @Email
    @NotBlank
    private String email;
}
