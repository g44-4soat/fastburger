package net.fiap.postech.fastburger.application.usecases;

import net.fiap.postech.fastburger.application.domain.Client;
import net.fiap.postech.fastburger.application.domain.HistoryOfOrder;
import net.fiap.postech.fastburger.application.ports.inputports.client.FindClientByCpfGateway;
import net.fiap.postech.fastburger.application.ports.inputports.client.SaveClientGateway;
import net.fiap.postech.fastburger.application.ports.inputports.history.FindHistoryByClientIdGateway;
import net.fiap.postech.fastburger.application.ports.inputports.history.SaveHistoryGateway;
import net.fiap.postech.fastburger.application.ports.outputports.client.FindClientByCpfOutPutPort;
import net.fiap.postech.fastburger.application.ports.outputports.client.SaveClientOutPutPort;
import net.fiap.postech.fastburger.application.ports.outputports.history.FindHistoryByClientIdOutPutPort;
import net.fiap.postech.fastburger.application.ports.outputports.history.SaveHistoryOutPutPort;

public class HistoryUseCase implements SaveHistoryGateway, FindHistoryByClientIdGateway {
    private final SaveHistoryOutPutPort saveHistoryOutPutPort;
    private final FindHistoryByClientIdOutPutPort findHistoryByClientIdOutPutPort;

    public HistoryUseCase(SaveHistoryOutPutPort saveHistoryOutPutPort, FindHistoryByClientIdOutPutPort findHistoryByClientIdOutPutPort) {
        this.saveHistoryOutPutPort = saveHistoryOutPutPort;
        this.findHistoryByClientIdOutPutPort = findHistoryByClientIdOutPutPort;
    }

    @Override
    public HistoryOfOrder find(String clientId) {
        return this.findHistoryByClientIdOutPutPort.find(clientId);
    }

    @Override
    public HistoryOfOrder save(HistoryOfOrder history) {
        return this.saveHistoryOutPutPort.save(history);
    }
}
