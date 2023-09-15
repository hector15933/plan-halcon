package org.plan.halcon.saldo.service.saldoservice.queries;

import org.plan.halcon.saldo.service.saldoservice.model.dao.AccountRepository;
import org.plan.halcon.saldo.service.saldoservice.model.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FindAccountBalanceHandler {

    @Autowired
    private AccountRepository accountRepository;

    public BigDecimal consultarSaldo(Long accountId) {

        Account account = accountRepository.findById(accountId).orElse(null);
        if (account != null) {
            return account.getBalance();
        } else {
            return null; // La cuenta no existe
        }
    }


}
