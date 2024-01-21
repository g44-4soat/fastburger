package net.fiap.postech.fastburger.adapters.persistence.repositories;

import net.fiap.postech.fastburger.adapters.persistence.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findClientEntityByCpf(String cpf);
}
