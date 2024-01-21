package net.fiap.postech.fastburger.adapters.feignClients.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PaymentDTO {
    @JsonProperty("id")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @JsonProperty("date_created")
    private Date dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @JsonProperty("date_last_updated")
    private Date dateLastUpdated;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @JsonProperty("date_of_expiration")
    private Date dateOfExpiration;

    @JsonProperty("money_release_status")
    private String moneyReleaseStatus;

    @JsonProperty("operation_type")
    private String operationType;

    @JsonProperty("payment_method_id")
    private String paymentMethodId;

    @JsonProperty("payment_type_id")
    private String paymentTypeId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("status_detail")
    private String statusDetail;

    @JsonProperty("currency_id")
    private String currencyId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("transaction_amount")
    private int transactionAmount;

    @JsonProperty("transaction_amount_refunded")
    private int transactionAmountRefunded;

    @JsonProperty("coupon_amount")
    private int couponAmount;

    @JsonProperty("callback_url")
    private String callbackUrl;

    @JsonProperty("processing_mode")
    private String processingMode;

    @JsonProperty("point_of_interaction")
    private PointOfInteractionDTO pointOfInteractionDTO;

}

