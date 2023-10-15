package br.com.fiap.challenge.domain.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandlerBodyFields {
    private String name;
    private String message;
}
