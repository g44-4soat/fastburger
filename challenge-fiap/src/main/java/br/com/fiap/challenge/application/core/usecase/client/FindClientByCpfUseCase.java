package br.com.fiap.challenge.application.core.usecase.client;

import br.com.fiap.challenge.application.core.domain.client.Client;
import br.com.fiap.challenge.application.ports.out.client.FindClientByCpfOutputPort;

public class FindClientByCpfUseCase {
    private final FindClientByCpfOutputPort findClientByCpfOutputPort;
    public FindClientByCpfUseCase(FindClientByCpfOutputPort findClientByCpfOutputPort) {
        this.findClientByCpfOutputPort = findClientByCpfOutputPort;
    }
    public Client findClientByCpf(String cpf) {
        return findClientByCpfOutputPort.find(cpf);
    }
}
