package net.fiap.postech.fastburger.adapters.persistence.repositories;

import net.fiap.postech.fastburger.adapters.persistence.entities.HistoryOfOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<HistoryOfOrderEntity, Long> {
    HistoryRepository findHistoryOfOrderEntityByClientId(Long clientId);
}
