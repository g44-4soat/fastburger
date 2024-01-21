package net.fiap.postech.fastburger.adapters.persistence.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDTO {

    private Long id;
    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;
}
