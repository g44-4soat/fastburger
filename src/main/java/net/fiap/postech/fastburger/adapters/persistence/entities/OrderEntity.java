package net.fiap.postech.fastburger.adapters.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import net.fiap.postech.fastburger.application.domain.enums.StatusOrder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;

    private Double totalValue;

    @UpdateTimestamp
    private LocalDateTime dateTimeCreation;

    @ManyToOne
    private ClientEntity client;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "orders_product",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private List<ProductEntity> products;

    @Enumerated(EnumType.STRING)
    private StatusOrder status;
}