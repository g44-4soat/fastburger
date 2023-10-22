package net.fiap.postech.fastburger.application.ports.inputports;

import net.fiap.postech.fastburger.application.domain.Client;
import net.fiap.postech.fastburger.application.ports.outputports.client.FindClientByCpfOutPutPort;
import net.fiap.postech.fastburger.application.ports.outputports.client.SaveClientOutPutPort;
import net.fiap.postech.fastburger.application.usecases.client.FindClientByCpfUseCase;
import net.fiap.postech.fastburger.application.usecases.client.SaveClientUseCase;

public class ClientGateway implements SaveClientUseCase, FindClientByCpfUseCase {
    private final SaveClientOutPutPort saveClientOutPutPort;
    private final FindClientByCpfOutPutPort findClientByCpfOutPutPort;

    public ClientGateway(SaveClientOutPutPort saveClientOutPutPort, FindClientByCpfOutPutPort findClientByCpfOutPutPort) {
        this.saveClientOutPutPort = saveClientOutPutPort;
        this.findClientByCpfOutPutPort = findClientByCpfOutPutPort;
    }

    @Override
    public Client find(String cpf) {
        return this.findClientByCpfOutPutPort.find(cpf);
    }

    @Override
    public Client save(Client client) {
        return this.saveClientOutPutPort.save(client);
    }
}
