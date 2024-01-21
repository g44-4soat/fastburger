package net.fiap.postech.fastburger.adapters.configuration.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

    @Value("${token.api.PaymentDataProcess}")
    private String token;

    public Boolean verifyToken(String token){
        return this.token.equalsIgnoreCase(token);
    }

}
