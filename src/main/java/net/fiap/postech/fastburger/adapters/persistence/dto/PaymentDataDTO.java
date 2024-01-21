package net.fiap.postech.fastburger.adapters.persistence.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@Builder
public class PaymentDataDTO {
    private String method;
    private String QRCode;
    private String ticketUrl;
    private String checkoutId;
    private Date createDate;
    private Date expirationDate;
    private Date lastUpdateDate;
    private String moneyReleaseStatus;
    private String operationType;
}
