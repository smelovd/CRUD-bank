package com.shimada36.bank;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RestController("/balance")
@AllArgsConstructor
public class BalanceController {

    private final BankService bankService;


    @GetMapping("/{accountId}")
    public BigDecimal getBalance(@PathVariable Long accountId) {
        return bankService.getBalance(accountId);
    }

    @PostMapping("/add")
    public BigDecimal addMoney(
            @RequestParam("to") Long toId,
            @RequestParam("amount") BigDecimal amount) {
        return bankService.addMoney(toId, amount);
    }

    @PostMapping("/operation")
    public void operation(
            @RequestParam("from") Long fromId,
            @RequestParam("to") Long toId,
            @RequestParam("amount") BigDecimal amount
            ) {
        bankService.doOperation(fromId, toId, amount);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handle(IllegalArgumentException e) {
        return "something went wrong with account...";
    }
}
