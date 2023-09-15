package org.plan.halcon.pagos.service.pagosservice.model.dao;

import org.plan.halcon.pagos.service.pagosservice.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IAccountRepository extends JpaRepository<Account,Long> {

}
