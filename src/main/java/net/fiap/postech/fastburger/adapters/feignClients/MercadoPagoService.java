package net.fiap.postech.fastburger.adapters.feignClients;

import net.fiap.postech.fastburger.adapters.feignClients.dto.PayerDTO;
import net.fiap.postech.fastburger.adapters.feignClients.dto.PaymentDTO;
import net.fiap.postech.fastburger.adapters.feignClients.dto.PaymentRequestDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.OrderDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.PaymentMethodDTO;
import net.fiap.postech.fastburger.application.domain.Client;
import net.fiap.postech.fastburger.application.ports.inputports.client.FindClientByCpfGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MercadoPagoService {

    @Value("${MERCADO_TOKEN}")
    private String bearerToken;
    private final FindClientByCpfGateway findClientByCpfGateway;
    private final MercadoPagoFeignClient mercadoPagoFeignClient;

    public MercadoPagoService(FindClientByCpfGateway findClientByCpfGateway, MercadoPagoFeignClient mercadoPagoFeignClient) {
        this.findClientByCpfGateway = findClientByCpfGateway;
        this.mercadoPagoFeignClient = mercadoPagoFeignClient;
    }

    public PaymentDTO generateQRCode(OrderDTO orderDTO, PaymentMethodDTO paymentMethodDTO) {
        Client client = null;
        PayerDTO payerDTO = new PayerDTO();
        PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO(orderDTO.getTotalValue().doubleValue(), paymentMethodDTO.getMethod().name().toLowerCase(), payerDTO);
        generateDescription(orderDTO, paymentRequestDTO);

        if (orderDTO.getClientCPF() != null) {
            client = this.findClientByCpfGateway.find(orderDTO.getClientCPF());
            payerDTO.setEmail(client.getEmail());
            payerDTO.setFirst_name(splitNome(client.getNome(), 1L));
            payerDTO.setLastName(splitNome(client.getNome(), 0L));
        } else {
            payerDTO.setEmail("fastburger@fiap.com");
            payerDTO.setFirst_name("fastburger - not informed");
            payerDTO.setLastName("fastburger - not informed");
        }

        ResponseEntity<PaymentDTO> paymentDTOResponseEntity = this.mercadoPagoFeignClient.generateQRCode(bearerToken, orderDTO.getOrderNumber(), paymentRequestDTO);
        return paymentDTOResponseEntity.getBody();
    }

    private static void generateDescription(OrderDTO orderDTO, PaymentRequestDTO paymentRequestDTO) {
        StringBuilder description = new StringBuilder();
        description.append("FASTBURGER");
        if (orderDTO.getClientCPF() != null) {
            description.append(" \n | CPF: ").append(orderDTO.getClientCPF()).append(" \n ");
        }
        description.append(" STATUS: " + orderDTO.getStatus().statusItemOrder).append(" -- Nº ORDEM: ").append(orderDTO.getOrderNumber());
        paymentRequestDTO.setDescription(description.toString());
    }

    private String splitNome(String nomeComplete, Long parte) {
        String[] partiesDoNome = nomeComplete.split("\\s+", 2);
        if (parte == 0) {
            return partiesDoNome[1];
        } else {
            return partiesDoNome[0];
        }
    }
}
