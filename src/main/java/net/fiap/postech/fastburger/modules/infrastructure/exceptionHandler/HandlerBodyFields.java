package net.fiap.postech.fastburger.modules.infrastructure.exceptionHandler;

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
