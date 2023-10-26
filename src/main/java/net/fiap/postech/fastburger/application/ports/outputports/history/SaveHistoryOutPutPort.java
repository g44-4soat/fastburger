package net.fiap.postech.fastburger.application.ports.outputports.history;

import net.fiap.postech.fastburger.application.domain.HistoryOfOrder;

public interface SaveHistoryOutPutPort {
    HistoryOfOrder save(HistoryOfOrder history);
}
