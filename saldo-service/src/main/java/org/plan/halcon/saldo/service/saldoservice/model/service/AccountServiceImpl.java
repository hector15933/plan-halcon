package org.plan.halcon.saldo.service.saldoservice.model.service;

import org.plan.halcon.saldo.service.saldoservice.model.dao.AccountRepository;
import org.plan.halcon.saldo.service.saldoservice.model.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService{

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    public BigDecimal consultarSaldo(Long id){
        Optional<Account> accountOptional=findById(id);
        return accountOptional.map(Account::getBalance).orElse(null);
    }
}
