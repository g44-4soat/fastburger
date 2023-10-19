package net.fiap.postech.fastburger.modules.infrastructure.exceptionHandler;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class HandlerBodyException {

    private String title;
    private Integer status;
    private OffsetDateTime dateTime;
    private List<HandlerBodyFields> fieldsList;

}
