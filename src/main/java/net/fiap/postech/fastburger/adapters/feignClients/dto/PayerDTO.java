package net.fiap.postech.fastburger.adapters.feignClients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PayerDTO {
    private String email;
    private String first_name;
    private String last_name;

    public PayerDTO(String email, String first_name, String last_name) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }
}

