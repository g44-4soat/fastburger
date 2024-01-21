package net.fiap.postech.fastburger.adapters.persistence.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDataDTO {
    private String method;
    private String QRCode;
    private String ticketUrl;
}
