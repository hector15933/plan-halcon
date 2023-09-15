package org.plan.halcon.pagos.service.pagosservice.model.service;

import org.plan.halcon.pagos.service.pagosservice.model.entity.Account;

import java.util.Optional;

public interface IAccountService {
    Optional<Account> findByUserId(Long id);

    void save(Optional<Account> accountOrigen);
}
