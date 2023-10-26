package net.fiap.postech.fastburger.adapters.history;

import net.fiap.postech.fastburger.adapters.configuration.exceptionHandler.ClientNotFoundException;
import net.fiap.postech.fastburger.adapters.persistence.mapper.ClientMapper;
import net.fiap.postech.fastburger.adapters.persistence.repositories.ClientRepository;
import net.fiap.postech.fastburger.adapters.persistence.repositories.HistoryRepository;
import net.fiap.postech.fastburger.application.domain.Client;
import net.fiap.postech.fastburger.application.domain.HistoryOfOrder;
import net.fiap.postech.fastburger.application.ports.inputports.history.FindHistoryByClientIdGateway;
import net.fiap.postech.fastburger.application.ports.outputports.client.FindClientByCpfOutPutPort;
import net.fiap.postech.fastburger.application.ports.outputports.history.FindHistoryByClientIdOutPutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindHistoryByClientIdAdapter implements FindHistoryByClientIdOutPutPort {
    private final HistoryRepository historyRepository;

    public FindHistoryByClientIdAdapter(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public HistoryOfOrder find(String clientId) {
        //return this.historyRepository.findHistoryOfOrderEntityByClientId(Long.parseLong(clientId));
        return null;
    }
}
