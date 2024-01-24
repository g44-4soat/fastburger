package net.fiap.postech.fastburger.adapters.feignClients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentRequestDTO {
    private double transaction_amount;
    private String description;
    private String payment_method_id;
    private PayerDTO payer;

    public PaymentRequestDTO(double transaction_amount, String payment_method_id, PayerDTO payerDTO) {
        this.transaction_amount = transaction_amount;
        this.payment_method_id = payment_method_id;
        this.payer = payerDTO;
    }

    @JsonProperty("transaction_amount")
    public double getTransactionAmount() {
        return transaction_amount;
    }

    public void setTransactionAmount(double transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    @JsonProperty("payment_method_id")
    public String getPaymentMethodId() {
        return payment_method_id;
    }

    public void setPaymentMethodId(String payment_method_id) {
        this.payment_method_id = payment_method_id;
    }

    @JsonProperty("payer")
    public PayerDTO getPayer() {
        return payer;
    }

    public void setPayer(PayerDTO payerDTO) {
        this.payer = payerDTO;
    }
}
