package net.fiap.postech.fastburger.adapters.history;

import net.fiap.postech.fastburger.adapters.persistence.mapper.ClientMapper;
import net.fiap.postech.fastburger.adapters.persistence.repositories.ClientRepository;
import net.fiap.postech.fastburger.adapters.persistence.repositories.HistoryRepository;
import net.fiap.postech.fastburger.application.domain.Client;
import net.fiap.postech.fastburger.application.domain.HistoryOfOrder;
import net.fiap.postech.fastburger.application.ports.outputports.client.SaveClientOutPutPort;
import net.fiap.postech.fastburger.application.ports.outputports.history.SaveHistoryOutPutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveHistoryAdapter implements SaveHistoryOutPutPort {
    private final HistoryRepository historyRepository;

    public SaveHistoryAdapter(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public HistoryOfOrder save(HistoryOfOrder history) {
        return null;
    }
}
