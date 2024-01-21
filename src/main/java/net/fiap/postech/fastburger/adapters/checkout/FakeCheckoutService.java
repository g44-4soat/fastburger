package net.fiap.postech.fastburger.adapters.checkout;

import net.fiap.postech.fastburger.adapters.configuration.exceptionHandler.BusinessException;
import net.fiap.postech.fastburger.adapters.feignClients.dto.PaymentDataProcess;
import net.fiap.postech.fastburger.adapters.feignClients.dto.PaymentStatus;
import net.fiap.postech.fastburger.adapters.persistence.dto.PaymentDataDTO;
import net.fiap.postech.fastburger.adapters.persistence.dto.PaymentMethodDTO;
import net.fiap.postech.fastburger.application.domain.Order;
import net.fiap.postech.fastburger.application.domain.enums.StatusOrder;
import net.fiap.postech.fastburger.application.ports.inputports.order.ListOrderByNumberGateway;
import net.fiap.postech.fastburger.application.ports.inputports.order.UpdateOrderGetway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import com.google.zxing.client.j2se.MatrixToImageWriter;

@Component
public class FakeCheckoutService implements CheckoutContract{
    private final UpdateOrderGetway updateOrderGetway;
    private final ListOrderByNumberGateway listOrderByNumberGateway;

    @Autowired
    public FakeCheckoutService(UpdateOrderGetway updateOrderGetway, ListOrderByNumberGateway listOrderByNumberGateway) {
        this.updateOrderGetway = updateOrderGetway;
        this.listOrderByNumberGateway = listOrderByNumberGateway;
    }

    public PaymentDataDTO payOrder(String orderNumber, PaymentMethodDTO paymentMethod) {
        Order order = this.listOrderByNumberGateway.listByNumber(orderNumber);
        order.setStatus(StatusOrder.INPREPARATION);
        order.setWasPaid(true);
        Order update = this.updateOrderGetway.update(orderNumber, order);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return PaymentDataDTO.builder()
                .method(paymentMethod.getMethod().getType())
                .QRCode(generateQRCode(orderNumber.concat(paymentMethod.getMethod().getType())))
                .build();
    }

    @Override
    public PaymentStatus paymentStatys(String orderNumber) {
        return null;
    }

    @Override
    public void processFallbackPayment(PaymentDataProcess paymentDataProcess, String token) {

    }

    public String generateQRCode(String content) {
        try {
            int width = 200;
            int height = 200;
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height);
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qrImage, "png", baos);
            byte[] imageBytes = baos.toByteArray();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            return base64Image;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
