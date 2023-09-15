package org.plan.halcon.pagos.service.pagosservice.model.service;

import org.plan.halcon.pagos.service.pagosservice.model.dao.IAccountRepository;
import org.plan.halcon.pagos.service.pagosservice.model.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService{

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public Optional<Account> findByUserId(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public void save(Optional<Account> accountOrigen) {
        accountRepository.save(accountOrigen.get());
    }
}
