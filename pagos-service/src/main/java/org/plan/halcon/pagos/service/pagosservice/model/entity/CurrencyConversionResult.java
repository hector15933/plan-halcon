package org.plan.halcon.pagos.service.pagosservice.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class CurrencyConversionResult {

    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("convertedAmount")
    private BigDecimal convertedAmount;
    @JsonProperty("currencyFrom")
    private String currencyFrom;
    @JsonProperty("currencyTo")
    private String currencyTo;
    @JsonProperty("exchangeRate")
    private BigDecimal exchangeRate;
}