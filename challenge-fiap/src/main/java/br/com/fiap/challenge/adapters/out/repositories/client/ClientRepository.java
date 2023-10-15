package br.com.fiap.challenge.adapters.out.repositories.client;

import br.com.fiap.challenge.adapters.out.repositories.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<ClientEntity, Long>, JpaRepository<ClientEntity, Long> {
    ClientEntity findClientByCpf(String cpf);
}
