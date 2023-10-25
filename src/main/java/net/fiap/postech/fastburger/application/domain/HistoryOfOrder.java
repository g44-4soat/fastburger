package net.fiap.postech.fastburger.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.fiap.postech.fastburger.application.domain.enums.StatusOrder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HistoryOfOrder {
    private String id;
    private StatusOrder status;
}
