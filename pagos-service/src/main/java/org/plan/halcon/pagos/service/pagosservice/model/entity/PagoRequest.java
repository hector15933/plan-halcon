package org.plan.halcon.pagos.service.pagosservice.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class PagoRequest {
    private Long accountFrom;
    private Long accountTo;
    private BigDecimal amount;
    private String currencyFrom;
    private String currencyTo;

}