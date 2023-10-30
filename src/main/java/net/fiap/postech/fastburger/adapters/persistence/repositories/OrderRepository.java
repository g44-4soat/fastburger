package net.fiap.postech.fastburger.adapters.persistence.repositories;

import net.fiap.postech.fastburger.adapters.persistence.entities.OrderEntity;
import net.fiap.postech.fastburger.application.domain.enums.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query("SELECT MAX(e.id) FROM OrderEntity e")
    Long getLastOrderId();
    OrderEntity findOrderEntityByOrderNumber(String orderNumber);

    List<OrderEntity> findOrderEntityByStatus(StatusOrder statusOrder);
}
