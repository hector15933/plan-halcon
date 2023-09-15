package org.plan.halcon.pagos.service.pagosservice.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class PagoResponse {
    private BigDecimal monto;
    private BigDecimal montoConTipoDeCambio;
    private String monedaOrigen;
    private String monedaDestino;
    private BigDecimal tipoDeCambio;

}