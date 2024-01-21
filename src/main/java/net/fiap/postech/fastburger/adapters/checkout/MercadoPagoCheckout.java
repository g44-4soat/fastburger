package net.fiap.postech.fastburger.adapters.checkout;

import net.fiap.postech.fastburger.adapters.feignClients.MercadoPagoService;
import net.fiap.postech.fastburger.adapters.feignClients.dto.PaymentDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.PaymentDataDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.PaymentMethodDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.enumerations.PayMentMethodEnum;
import net.fiap.postech.fastburger.adapters.persistence.mapper.OrderMapper;
import net.fiap.postech.fastburger.application.domain.Order;
import net.fiap.postech.fastburger.application.domain.enums.StatusOrder;
import net.fiap.postech.fastburger.application.ports.inputports.order.ListOrderByNumberGateway;
import net.fiap.postech.fastburger.application.ports.inputports.order.UpdateOrderGetway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MercadoPagoCheckout implements CheckoutContract {
    private final MercadoPagoService mercadoPagoService;

    private final OrderMapper orderMapper;
    private final UpdateOrderGetway updateOrderGetway;
    private final ListOrderByNumberGateway listOrderByNumberGateway;

    @Autowired
    public MercadoPagoCheckout(MercadoPagoService mercadoPagoService, OrderMapper orderMapper, UpdateOrderGetway updateOrderGetway, ListOrderByNumberGateway listOrderByNumberGateway) {
        this.mercadoPagoService = mercadoPagoService;
        this.orderMapper = orderMapper;
        this.updateOrderGetway = updateOrderGetway;
        this.listOrderByNumberGateway = listOrderByNumberGateway;
    }

    public PaymentDataDTO payOrder(String orderNumber, PaymentMethodDTO paymentMethodDTO) {
        return getPaymentDataDTO(orderNumber, paymentMethodDTO);
    }

    private PaymentDataDTO getPaymentDataDTO(String orderNumber, PaymentMethodDTO paymentMethodDTO) {
        PaymentDataDTO paymentDataDTO;
        if (PayMentMethodEnum.CASH.name().equals(paymentMethodDTO.getMethod().getType())) {
            paymentDataDTO = PaymentDataDTO.builder()
                    .ticketUrl("was paid with cash")
                    .QRCode("was paid with cash")
                    .method(paymentMethodDTO.getMethod().name())
                    .build();
        } else if (PayMentMethodEnum.CARD.name().equals(paymentMethodDTO.getMethod().getType())) {
            paymentDataDTO = PaymentDataDTO.builder()
                    .ticketUrl("was paid with card")
                    .QRCode("was paid with card")
                    .method(paymentMethodDTO.getMethod().name())
                    .build();
        } else {
            paymentDataDTO = payWithPixOrCard(orderNumber, paymentMethodDTO);
        }
        return paymentDataDTO;
    }

    private PaymentDataDTO payWithPixOrCard(String orderNumber, PaymentMethodDTO paymentMethodDTO) {
        PaymentDTO paymentDTO;
        try {
            Order order = this.listOrderByNumberGateway.listByNumber(orderNumber);
            order.setStatus(StatusOrder.INPREPARATION);
            order.setWasPaid(true);
            Order update = this.updateOrderGetway.update(orderNumber, order);
            paymentDTO = this.mercadoPagoService.generateQRCode(this.orderMapper.orderToOrderDTO(order), paymentMethodDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return PaymentDataDTO.builder()
                .method(paymentMethodDTO.getMethod().getType())
                .QRCode(paymentDTO.getPointOfInteractionDTO().getTransactionDataDTO().getQrCode())
                .ticketUrl(paymentDTO.getPointOfInteractionDTO().getTransactionDataDTO().getTicketUrl())
                .build();
    }
}
