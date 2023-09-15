package org.plan.halcon.pagos.service.pagosservice.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountFrom; // ID de la cuenta de origen
    private Long accountTo;   // ID de la cuenta de destino
    private BigDecimal amount; // Monto de la transacción
    private String currencyFrom; // Moneda de origen
    private String currencyTo;   // Moneda de destino
    private BigDecimal exchangeRate; // Tasa de cambio utilizada
    private Date timestamp; // Fecha y hora de la transacción

}