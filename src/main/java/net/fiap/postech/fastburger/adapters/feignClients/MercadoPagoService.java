package net.fiap.postech.fastburger.adapters.feignClients;

import net.fiap.postech.fastburger.adapters.feignClients.dto.PayerDTO;
import net.fiap.postech.fastburger.adapters.feignClients.dto.PaymentDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.ClientDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MercadoPagoService {

    @Value("${mercado-pago.token}")
    private String bearerToken;

    private final MercadoPagoFeignClient mercadoPagoFeignClient;

    public MercadoPagoService(MercadoPagoFeignClient mercadoPagoFeignClient) {
        this.mercadoPagoFeignClient = mercadoPagoFeignClient;
    }

    public PaymentDTO generateQRCode(OrderDTO orderDTO){
        PayerDTO payer = new PayerDTO(orderDTO.getClientCPF(), orderDTO.getcl)
    }

}
