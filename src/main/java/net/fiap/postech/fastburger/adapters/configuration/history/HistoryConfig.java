package net.fiap.postech.fastburger.adapters.configuration.history;

import net.fiap.postech.fastburger.adapters.client.FindClientAdapter;
import net.fiap.postech.fastburger.adapters.client.SaveClientAdapter;
import net.fiap.postech.fastburger.adapters.history.FindHistoryByClientIdAdapter;
import net.fiap.postech.fastburger.adapters.history.SaveHistoryAdapter;
import net.fiap.postech.fastburger.application.ports.outputports.history.FindHistoryByClientIdOutPutPort;
import net.fiap.postech.fastburger.application.ports.outputports.history.SaveHistoryOutPutPort;
import net.fiap.postech.fastburger.application.usecases.ClientUseCase;
import net.fiap.postech.fastburger.application.usecases.HistoryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HistoryConfig {

    @Bean
    public HistoryUseCase historyUseCase(SaveHistoryAdapter saveHistoryAdapter, FindHistoryByClientIdAdapter findHistoryByClientIdAdapter) {
        return new HistoryUseCase(saveHistoryAdapter, findHistoryByClientIdAdapter);
    }

}
