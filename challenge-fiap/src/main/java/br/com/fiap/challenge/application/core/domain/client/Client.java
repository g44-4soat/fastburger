package br.com.fiap.challenge.application.core.domain.client;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;
}
