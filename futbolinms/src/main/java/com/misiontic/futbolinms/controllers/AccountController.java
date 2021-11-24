package com.misiontic.futbolinms.controllers;

import com.misiontic.futbolinms.exceptions.AccountNotFoundException;
import com.misiontic.futbolinms.models.Account;
import com.misiontic.futbolinms.repositories.AccountRepository;
import org.springframework.web.bind.annotation.*;

@RestController

public class AccountController {
    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @GetMapping("/accounts/{username}")
    Account getAccount(@PathVariable String username){
        return accountRepository.findById(username)
                .orElseThrow(() -> new AccountNotFoundException("No se encontro una cuenta con el username: " + username));
    }
    @PostMapping("/accounts")
    Account newAccount(@RequestBody Account account){
        return accountRepository.save(account);
    }

}
