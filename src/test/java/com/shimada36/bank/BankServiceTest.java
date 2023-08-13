package com.shimada36.bank;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BankServiceTest {

    private BalanceRepository balanceRepository = new BalanceRepository();
    private BankService bankService = new BankService(balanceRepository);

    @Test
    void getBalance() {
        final BigDecimal balance = bankService.getBalance(1L);
        assertEquals(balance, BigDecimal.TEN);
    }
    @Test
    void addMoney() {
        final BigDecimal balance = bankService.addMoney(1L, BigDecimal.ONE);
        assertEquals(balance, BigDecimal.valueOf(11));
    }
}