package org.plan.halcon.pagos.service.pagosservice.model.service;

import antlr.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.plan.halcon.pagos.service.pagosservice.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class PaymentService {

   @Autowired
   private IAccountService accountService;

    @Value("${app.saldo.service.url}")
    private String serviceUrl;
    RestTemplate restTemplate= new RestTemplate();

    @Transactional
    public PagoResponse realizarPago(PagoRequest pagoRequest) {

        Long accountFromId = pagoRequest.getAccountFrom();
        Long accountToId = pagoRequest.getAccountTo();
        BigDecimal amount = pagoRequest.getAmount();
        String monedaOrigen = pagoRequest.getCurrencyFrom();
        String monedaDestino = pagoRequest.getCurrencyTo();

        Optional<Account> accountOrigen = accountService.findByUserId(pagoRequest.getAccountFrom());
        if (accountOrigen.isPresent() && accountOrigen.get().getBalance().compareTo(pagoRequest.getAmount()) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        Optional<Account> accountDestino = accountService.findByUserId(pagoRequest.getAccountTo());

        BigDecimal tipoCambio = obtenerTipoCambio(monedaOrigen, monedaDestino);

        BigDecimal montoConTipoCambio = amount.multiply(tipoCambio);

        accountOrigen.get().setBalance(accountOrigen.get().getBalance().subtract(pagoRequest.getAmount()));
        accountDestino.get().setBalance(accountDestino.get().getBalance().add(pagoRequest.getAmount()));

        // Guardar ambos cambios en la base de datos
        accountService.save(accountOrigen);
        accountService.save(accountDestino);

        PagoResponse response = new PagoResponse();
        response.setMonto(amount);
        response.setMontoConTipoDeCambio(montoConTipoCambio);
        response.setMonedaOrigen(monedaOrigen);
        response.setMonedaDestino(monedaDestino);
        response.setTipoDeCambio(tipoCambio);

        return response;
    }

    private BigDecimal obtenerTipoCambio(String monedaOrigen, String monedaDestino) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add(HttpHeaders.AUTHORIZATION,"Bearer "+ obtenerToken());
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);


        String url= "http://"+serviceUrl+"/exchangeRate?currencyFrom="+monedaOrigen+"&currencyTo="+monedaDestino+"&amount="+1000.00;
        ResponseEntity<CurrencyConversionResult> responseHttp= restTemplate.exchange(url,HttpMethod.GET,requestEntity,CurrencyConversionResult.class);

        CurrencyConversionResult currencyConversionResult=responseHttp.getBody();
        // Supongamos que obtienes el tipo de cambio de 1 USD a EUR
        return currencyConversionResult.getExchangeRate();
    }

    private boolean realizarPago(Long accountFromId, BigDecimal amount, Long accountToId) {

        return true;
    }


    private String obtenerToken(){
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE,"application/x-www-form-urlencoded");
        headers.add(HttpHeaders.AUTHORIZATION,"Basic YW5ndWxhcmFwcDoxMjM0NQ==");
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("password","123456");
        map.add("username","user123");
        map.add("grant_type","password");
        HttpEntity<?> requestEntity = new HttpEntity<>(map,headers);

        String urlToken = "http://localhost:8080/oauth/token";

        ResponseEntity<TokenDto> response = restTemplate.exchange(urlToken,HttpMethod.POST,requestEntity, TokenDto.class);
        TokenDto tokenDto= response.getBody();

        return tokenDto.getAccessToken();
    }
}
