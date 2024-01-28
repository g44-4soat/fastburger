package net.fiap.postech.fastburger.adapters.configuration.paymentapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentApiConfig {

    @Value("${mercado-pago.api}")
    private String mercadoApi;

    public String getMercadoApi() {
        return mercadoApi;
    }
}
