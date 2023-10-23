package net.fiap.postech.fastburger.adapters.configuration.client;

import net.fiap.postech.fastburger.adapters.client.FindClientAdapter;
import net.fiap.postech.fastburger.adapters.client.SaveClientAdapter;
import net.fiap.postech.fastburger.application.usecases.ClientUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    public ClientUseCase clientUseCase(SaveClientAdapter saveClientAdapter, FindClientAdapter findClientAdapter) {
        return new ClientUseCase(saveClientAdapter, findClientAdapter);
    }

}
