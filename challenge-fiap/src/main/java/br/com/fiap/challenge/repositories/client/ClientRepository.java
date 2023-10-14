package br.com.fiap.challenge.repositories.client;

import br.com.fiap.challenge.domain.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long>, JpaRepository<Client, Long> {
    Client findClientByCpf(String cpf);
}
