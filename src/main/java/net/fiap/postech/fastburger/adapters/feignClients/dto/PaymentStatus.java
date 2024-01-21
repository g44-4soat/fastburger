package net.fiap.postech.fastburger.adapters.feignClients.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentStatus {
    private String orderId;
    private boolean paymentWasApproved;
}
