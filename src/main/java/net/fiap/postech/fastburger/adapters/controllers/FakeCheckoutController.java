package net.fiap.postech.fastburger.adapters.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.fiap.postech.fastburger.adapters.checkout.FakeCheckoutService;
import net.fiap.postech.fastburger.adapters.persistence.dto.PaymentMethodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/checkout")
@Tag(name = "Checkout Controller Rest")
public class FakeCheckoutController {

    private final FakeCheckoutService fakeCheckoutService;

    @Autowired
    public FakeCheckoutController(FakeCheckoutService fakeCheckoutService) {
        this.fakeCheckoutService = fakeCheckoutService;
    }

    @PostMapping("orderNumber/{orderNumber}")
    public ResponseEntity payOrder(@PathVariable("orderNumber") String orderNumber, PaymentMethodDTO paymentMethodDTO) {
        boolean wasSucc = this.fakeCheckoutService.payOrder(orderNumber, paymentMethodDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
