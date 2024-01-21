package net.fiap.postech.fastburger.adapters.feignClients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PointOfInteractionDTO {

    @JsonProperty("transaction_data")
    private TransactionDataDTO transactionDataDTO;
}
