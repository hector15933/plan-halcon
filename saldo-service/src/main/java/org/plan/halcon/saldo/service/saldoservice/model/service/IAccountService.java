package org.plan.halcon.saldo.service.saldoservice.model.service;

import org.plan.halcon.saldo.service.saldoservice.model.entity.Account;

import java.util.Optional;

public interface IAccountService {

    Optional<Account> findById(Long id);
}
