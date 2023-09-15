package org.plan.halcon.pagos.service.pagosservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.plan.halcon.pagos.service.pagosservice.model.entity.Account;
import org.plan.halcon.pagos.service.pagosservice.model.entity.PagoRequest;
import org.plan.halcon.pagos.service.pagosservice.model.entity.PagoResponse;
import org.plan.halcon.pagos.service.pagosservice.model.service.IAccountService;
import org.plan.halcon.pagos.service.pagosservice.model.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@RestController
public class PagoController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private IAccountService accountService;

    private final RestTemplate restTemplate= new RestTemplate();


    @PostMapping("realizar-pago")
    public ResponseEntity<?> realizarPago(@RequestBody PagoRequest pagoRequest) {

        Map<String,Object> response = new HashMap<>();

        try{
            Optional<Account> accountOrigenOptional = accountService.findByUserId(pagoRequest.getAccountFrom());
            Optional<Account> accountTargetOptional = accountService.findByUserId(pagoRequest.getAccountTo());

            if(accountOrigenOptional.isPresent() && accountTargetOptional.isPresent()) {
                BigDecimal saldo = getSaldoFromService(pagoRequest.getAccountFrom());
                if (saldo != null && saldo.compareTo(pagoRequest.getAmount()) < 0) {
                    response.put("error: ", "Saldo Insuficiente");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
                } else {
                        PagoResponse response2 = paymentService.realizarPago(pagoRequest);
                        return ResponseEntity.ok(response2);
                }
            }else{
                response.put("error: ", "El usuario no existe");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            response.put("error: ",e.getMessage());
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

    }

    public BigDecimal getSaldoFromService(Long id){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<BigDecimal> responseHttp =  restTemplate.exchange(
                "http://localhost:8081/api/saldo/consultar-saldo"+"?account_id="+id,
                HttpMethod.GET,requestEntity,BigDecimal.class);
        return responseHttp.getBody();
    }

}
