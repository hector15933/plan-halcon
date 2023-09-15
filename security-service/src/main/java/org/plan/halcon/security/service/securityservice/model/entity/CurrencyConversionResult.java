package org.plan.halcon.security.service.securityservice.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class CurrencyConversionResult {
    private BigDecimal amount;
    private BigDecimal convertedAmount;
    private String currencyFrom;
    private String currencyTo;
    private BigDecimal exchangeRate;


}