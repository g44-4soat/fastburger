package net.fiap.postech.fastburger.adapters.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import net.fiap.postech.fastburger.application.domain.enums.StatusOrder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
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
    private Double totalValue = BigDecimal.ZERO.doubleValue();
    private Boolean wasPaid = false;
    @UpdateTimestamp
    private LocalDateTime dateTimeCreation;
    @ManyToOne
    private ClientEntity client;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_itens",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "itens_id")}
    )
    private List<OrderItemEntity> orderItems;
    @Enumerated(EnumType.STRING)
    private StatusOrder status;
}
