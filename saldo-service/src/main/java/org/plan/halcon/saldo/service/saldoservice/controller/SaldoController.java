package org.plan.halcon.saldo.service.saldoservice.controller;

import org.plan.halcon.saldo.service.saldoservice.model.entity.Account;
import org.plan.halcon.saldo.service.saldoservice.model.service.IAccountService;
import org.plan.halcon.saldo.service.saldoservice.model.service.SaldoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/saldo")
public class SaldoController{

    @Autowired
    private IAccountService saldoService;

    @GetMapping("/consultar-saldo")
    public ResponseEntity<?> consultarSaldo(@RequestParam Long account_id) {
        Map<String,Object> response = new HashMap<>();
        BigDecimal saldo= null;
        Optional<Account> accountOptional=saldoService.findById(account_id);
        if(accountOptional.isPresent()){
            saldo = accountOptional.get().getBalance();
            return new ResponseEntity<>(saldo, HttpStatus.OK);
        }else {
            response.put("error","No se encontro una cuenta asociada a este id");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
