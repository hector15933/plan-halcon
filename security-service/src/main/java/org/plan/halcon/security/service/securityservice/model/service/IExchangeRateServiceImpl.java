package org.plan.halcon.security.service.securityservice.model.service;

import org.plan.halcon.security.service.securityservice.model.dao.IExchangeRateRepository;
import org.plan.halcon.security.service.securityservice.model.entity.ExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class IExchangeRateServiceImpl implements IExchangeRateService{

    @Autowired
    private IExchangeRateRepository iExchangeRateRepository;
    @Override
    public Optional<ExchangeRate> findByCurrencyFromAndCurrencyTo(String currencyFrom, String currencyTo) {
        return iExchangeRateRepository.findByCurrencyFromAndCurrencyTo(currencyFrom,currencyTo);
    }

    @Override
    public ExchangeRate updateExchangeRate(Long id, BigDecimal newRate) {
        ExchangeRate existingRate = iExchangeRateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de cambio no encontrado"));

        existingRate.setRate(newRate);
        return iExchangeRateRepository.save(existingRate);
    }
}
