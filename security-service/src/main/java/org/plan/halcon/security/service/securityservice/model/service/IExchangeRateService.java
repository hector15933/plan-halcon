package org.plan.halcon.security.service.securityservice.model.service;

import org.plan.halcon.security.service.securityservice.model.entity.ExchangeRate;

import java.math.BigDecimal;
import java.util.Optional;

public interface IExchangeRateService {
    Optional<ExchangeRate> findByCurrencyFromAndCurrencyTo(String currencyFrom, String currencyTo);
    ExchangeRate updateExchangeRate(Long id, BigDecimal newRate);
}
