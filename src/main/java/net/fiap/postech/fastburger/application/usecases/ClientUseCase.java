package net.fiap.postech.fastburger.application.usecases;

import net.fiap.postech.fastburger.application.domain.Client;
import net.fiap.postech.fastburger.application.ports.outputports.client.FindClientByCpfOutPutPort;
import net.fiap.postech.fastburger.application.ports.outputports.client.SaveClientOutPutPort;

public class ClientUseCase {
    private final SaveClientOutPutPort saveClientOutPutPort;
    private final FindClientByCpfOutPutPort findClientByCpfOutPutPort;

    public ClientUseCase(SaveClientOutPutPort saveClientOutPutPort, FindClientByCpfOutPutPort findClientByCpfOutPutPort) {
        this.saveClientOutPutPort = saveClientOutPutPort;
        this.findClientByCpfOutPutPort = findClientByCpfOutPutPort;
    }

    public Client find(String cpf) {
        return this.findClientByCpfOutPutPort.find(cpf);
    }

    public Client save(Client client) {
        return this.saveClientOutPutPort.save(client);
    }
}
