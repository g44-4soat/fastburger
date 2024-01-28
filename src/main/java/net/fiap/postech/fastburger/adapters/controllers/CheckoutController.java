package net.fiap.postech.fastburger.adapters.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.fiap.postech.fastburger.adapters.checkout.CheckoutContract;
import net.fiap.postech.fastburger.adapters.checkout.MercadoPagoCheckout;
import net.fiap.postech.fastburger.adapters.feignClients.dto.PaymentDataProcess;
import net.fiap.postech.fastburger.adapters.feignClients.dto.PaymentStatus;
import net.fiap.postech.fastburger.adapters.persistence.dto.PaymentDataDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.PaymentMethodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/checkout")
@Tag(name = "Checkout Controller Rest")
public class CheckoutController {
    private final CheckoutContract mercadoPagoCheckout;

    @Autowired
    public CheckoutController(MercadoPagoCheckout mercadoPagoCheckout) {
        this.mercadoPagoCheckout = mercadoPagoCheckout;
    }

    @PostMapping("orderNumber/{orderNumber}")
    public ResponseEntity<PaymentDataDTO> payOrder(@PathVariable("orderNumber") String orderNumber, PaymentMethodDTO paymentMethodDTO) {
        PaymentDataDTO paymentDataDTO = this.mercadoPagoCheckout.payOrder(orderNumber, paymentMethodDTO);
        return ResponseEntity.status(HttpStatus.OK).body(paymentDataDTO);
    }

    @GetMapping("/payment/status/{orderNumber}")
    public ResponseEntity<PaymentStatus> getPaymentStatus(@PathVariable("orderNumber") String orderNumber) {
        PaymentStatus paymentStatus = this.mercadoPagoCheckout.paymentStatys(orderNumber);
        return ResponseEntity.status(HttpStatus.OK).body(paymentStatus);
    }

    @PostMapping("/payment/fallback/{orderNumber}")
    public ResponseEntity<Void> fallbackPayment(@RequestBody PaymentDataProcess paymentDataProcess){
        this.mercadoPagoCheckout.processFallbackPayment(paymentDataProcess, null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
