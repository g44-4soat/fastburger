package net.fiap.postech.fastburger.adapters.checkout;

import net.fiap.postech.fastburger.adapters.persistence.dto.PaymentDataDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.PaymentMethodDTO;

public interface CheckoutContract {
    PaymentDataDTO payOrder(String orderNumber, PaymentMethodDTO paymentMethodDTO);
}
