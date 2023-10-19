package net.fiap.postech.fastburger.modules.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private Long id;
    private String cpf;
    private String nome;
    private String email;
}
