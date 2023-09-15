package org.plan.halcon.saldo.service.saldoservice.model.dao;

import org.plan.halcon.saldo.service.saldoservice.model.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account,Long> {
}
