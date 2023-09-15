package org.plan.halcon.security.service.securityservice.controller;

import org.plan.halcon.security.service.securityservice.model.entity.CurrencyConversionResult;
import org.plan.halcon.security.service.securityservice.model.entity.ExchangeRate;
import org.plan.halcon.security.service.securityservice.model.entity.Usuario;
import org.plan.halcon.security.service.securityservice.model.service.IExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class ExchangeRateController {


    @Autowired
    private IExchangeRateService exchangeRateService;


    @GetMapping("/exchangeRate")
    public ResponseEntity<?> convertCurrency(@RequestParam String currencyFrom, @RequestParam String currencyTo, @RequestParam   BigDecimal amount) {

        Map<String,Object> response = new HashMap<>();

        Optional<ExchangeRate> exchangeRateOptional = exchangeRateService
                .findByCurrencyFromAndCurrencyTo(currencyFrom, currencyTo);

        if (exchangeRateOptional.isPresent()) {
            ExchangeRate exchangeRate = exchangeRateOptional.get();
            BigDecimal convertedAmount = amount.multiply(exchangeRate.getRate());

            CurrencyConversionResult result = new CurrencyConversionResult();
            result.setAmount(amount);
            result.setConvertedAmount(convertedAmount);
            result.setCurrencyFrom(currencyFrom);
            result.setCurrencyTo(currencyTo);
            result.setExchangeRate(exchangeRate.getRate());

            return new ResponseEntity<CurrencyConversionResult>(result, HttpStatus.OK);
        } else {
            response.put("error","No se encontró una tasa de cambio válida.");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ExchangeRate> updateExchangeRate(@RequestParam Long id, @RequestBody ExchangeRate request) {
        ExchangeRate updatedExchangeRate = exchangeRateService.updateExchangeRate(id, request.getRate());
        return ResponseEntity.ok(updatedExchangeRate);
    }

}
