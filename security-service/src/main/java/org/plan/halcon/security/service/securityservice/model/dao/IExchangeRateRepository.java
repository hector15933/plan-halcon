package org.plan.halcon.security.service.securityservice.model.dao;

import org.plan.halcon.security.service.securityservice.model.entity.ExchangeRate;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IExchangeRateRepository extends CrudRepository<ExchangeRate,Long> {
        Optional<ExchangeRate> findByCurrencyFromAndCurrencyTo(String currencyFrom, String currencyTo);
}
