package net.fiap.postech.fastburger.application.ports.inputports.history;

import net.fiap.postech.fastburger.application.domain.Client;
import net.fiap.postech.fastburger.application.domain.HistoryOfOrder;

public interface SaveHistoryGateway {
    HistoryOfOrder save(HistoryOfOrder history);
}
