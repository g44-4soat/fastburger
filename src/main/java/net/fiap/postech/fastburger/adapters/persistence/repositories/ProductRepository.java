package net.fiap.postech.fastburger.adapters.persistence.repositories;

import net.fiap.postech.fastburger.adapters.persistence.entities.ProductEntity;
import net.fiap.postech.fastburger.application.domain.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findProductEntityByCategoryEnum(CategoryEnum categoryEnum);
}
