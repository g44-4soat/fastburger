package net.fiap.postech.fastburger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Challenge Pos Tech - Fiap", description = "Desafio Pos Tech Arquitetura de Software (SOAT-04) G44", version = "1.0.0"))
public class FastburgerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastburgerApplication.class, args);
    }

}
