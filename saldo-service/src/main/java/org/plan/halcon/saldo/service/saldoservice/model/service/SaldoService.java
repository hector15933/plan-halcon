package org.plan.halcon.saldo.service.saldoservice.model.service;

import org.plan.halcon.saldo.service.saldoservice.model.dao.AccountRepository;
import org.plan.halcon.saldo.service.saldoservice.model.entity.Account;
import org.plan.halcon.saldo.service.saldoservice.model.entity.PagoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class SaldoService {

    @Autowired
    private AccountRepository accountRepository;
    @Transactional // Utilizamos transacciones para asegurar la atomicidad de la operación
    public BigDecimal realizarPago(PagoRequest pagoRequest) {
        Long accountFromId = pagoRequest.getAccountFrom();
        Long accountToId = pagoRequest.getAccountTo();
        BigDecimal amount = pagoRequest.getAmount();

        // Verificar si la cuenta de origen tiene fondos suficientes
        Account accountFrom = accountRepository.findById(accountFromId).orElse(null);
        if (accountFrom == null || accountFrom.getBalance().compareTo(amount) < 0) {
            // No hay fondos suficientes, puedes manejar el extorno aquí si es necesario
            return null; // Indicar que la transacción ha fallado
        }

        // Realizar la transacción y actualizar los saldos
        Account accountTo = accountRepository.findById(accountToId).orElse(null);
        if (accountTo == null) {
            // La cuenta de destino no existe, puedes manejar el extorno aquí si es necesario
            return null; // Indicar que la transacción ha fallado
        }

        // Realizar la transferencia desde la cuenta de origen a la cuenta de destino
        accountFrom.setBalance(accountFrom.getBalance().subtract(amount));
        accountTo.setBalance(accountTo.getBalance().add(amount));

        // Guardar los cambios en la base de datos
        accountRepository.save(accountFrom);
        accountRepository.save(accountTo);

        // Registrar la transacción, si es necesario

        // Devolver el nuevo saldo de la cuenta de origen
        return accountFrom.getBalance();
    }

    public BigDecimal consultarSaldo(Long accountId) {
        // Implementa la lógica para consultar el saldo de la cuenta por su ID
        // Puedes utilizar el repositorio para acceder a la base de datos u otra fuente de datos
        Account account = accountRepository.findById(accountId).orElse(null);

        if (account != null) {
            return account.getBalance();
        } else {
            return null; // La cuenta no existe
        }
    }
}
