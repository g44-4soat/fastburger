package br.com.fiap.challenge.repositories.product;

import br.com.fiap.challenge.domain.enumerations.CategoryEnum;
import br.com.fiap.challenge.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
    List<Product> findByCategoryEnum(CategoryEnum categoryEnum);
}
