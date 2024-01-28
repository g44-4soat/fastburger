package net.fiap.postech.fastburger.adapters.feignClients;

import net.fiap.postech.fastburger.adapters.feignClients.dto.PaymentDTO;
import net.fiap.postech.fastburger.adapters.feignClients.dto.PaymentRequestDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "mercado-api", url = "${MERCADO_API}")
public interface MercadoPagoFeignClient {

    @PostMapping("/v1/payments")
    ResponseEntity<PaymentDTO> generateQRCode(
            @RequestHeader(value = "Authorization", required = true) String authorizationHeader,
            @RequestHeader(value = "X-Idempotency-Key", required = true) String CodeKey,
            @RequestBody PaymentRequestDTO body);

    @RequestMapping(method = RequestMethod.GET, value = "/v1/payment_methods")
    @Cacheable("placementUseCase")
    ResponseEntity<Object> getPaymentMethods(@RequestHeader(value = "Authorization", required = true) String authorizationHeader);
}
