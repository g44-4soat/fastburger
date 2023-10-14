package br.com.fiap.challenge;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Challenge Pos Tech - Fiap", description = "Desafio Pos Tech Arquitetura de Software (SOAT-04) G44", version = "1.0.0"))
public class ChallengeFiapApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChallengeFiapApplication.class, args);
    }

}
