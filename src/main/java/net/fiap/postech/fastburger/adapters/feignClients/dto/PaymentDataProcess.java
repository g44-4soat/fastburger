package net.fiap.postech.fastburger.adapters.feignClients.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDataProcess {
    private String orderId;
    private String PaymentStatus;
    private boolean paymentWasApproved;
}
