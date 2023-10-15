package br.com.fiap.challenge.domain.exception.handler;

import lombok.Data;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class HandlerBodyException {

    private String title;
    private Integer status;
    private OffsetDateTime dateTime;
    private List<HandlerBodyFields> fieldsList;

}
