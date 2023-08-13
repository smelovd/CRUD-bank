package com.shimada36.bank;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class BankService {

    private final BalanceRepository repository;

    public BigDecimal getBalance(Long accountId) {
        BigDecimal balance = repository.getBalanceForId(accountId);
        if (balance == null) {
            throw new IllegalArgumentException("get balance");
        }
        return balance;
    }

    public BigDecimal addMoney(Long to, BigDecimal amount) {
        BigDecimal currentBalance = repository.getBalanceForId(to);
        if (currentBalance == null) {
            repository.save(to, amount);
            return amount;
        } else {
            BigDecimal updatedBalance = currentBalance.add(amount);
            repository.save(to,updatedBalance);
            return updatedBalance;
        }
    }

    public void doOperation(Long fromId, Long toId, BigDecimal amount) {
        BigDecimal fromBalance = repository.getBalanceForId(fromId);
        BigDecimal toBalance = repository.getBalanceForId(toId);
        if (fromBalance == null || toBalance == null) {
            throw new IllegalArgumentException("no ids");
        }
        if (amount.compareTo(fromBalance) > 0) {
            throw new IllegalArgumentException("No enough money");
        }

        final BigDecimal updatedFromBalance = fromBalance.subtract(amount);
        final BigDecimal updatedToBalance = toBalance.add(amount);
        repository.save(fromId, updatedFromBalance);
        repository.save(toId, updatedToBalance);
    }
}
