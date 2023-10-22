package net.fiap.postech.fastburger.application.ports.outputports.client;

import net.fiap.postech.fastburger.application.domain.Client;

public interface FindClientByCpfOutPutPort {
    Client find(String cpf);
}
