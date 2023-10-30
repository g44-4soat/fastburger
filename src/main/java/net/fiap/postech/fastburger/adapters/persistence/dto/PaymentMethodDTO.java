package net.fiap.postech.fastburger.adapters.persistence.dto;

import lombok.Data;
import net.fiap.postech.fastburger.adapters.persistence.dto.enumerations.PayMentMethodEnum;

@Data
public class PaymentMethodDTO {
    private PayMentMethodEnum method;
}
