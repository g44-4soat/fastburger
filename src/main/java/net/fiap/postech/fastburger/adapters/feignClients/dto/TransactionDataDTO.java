package net.fiap.postech.fastburger.adapters.feignClients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionDataDTO {

    @JsonProperty("qr_code")
    private String qrCode;

    @JsonProperty("ticket_url")
    private String ticketUrl;

    @JsonProperty("qr_code_base64")
    private String qrCodeBase64;
}
