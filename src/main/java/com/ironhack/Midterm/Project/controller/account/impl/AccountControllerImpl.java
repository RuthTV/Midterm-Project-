package com.ironhack.Midterm.Project.controller.account.impl;

import com.ironhack.Midterm.Project.controller.account.dto.Transference;
import com.ironhack.Midterm.Project.controller.account.interfaces.AccountController;
import com.ironhack.Midterm.Project.model.accounts.Money;
import com.ironhack.Midterm.Project.repositories.accountRepository.AccountRepository;
import com.ironhack.Midterm.Project.service.account.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AccountControllerImpl implements AccountController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountService accountService;

//    @PatchMapping("/accounts/{id1}/accounts/{id2}/balance/{balance}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void transferBalance(@PathVariable Long id1, @PathVariable Long id2, @RequestBody @Valid Money balance) {
//        accountService.transferBalance(id1, id2, balance.getBalance());
//    }
    @PutMapping("/transference")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transferBalance(@RequestBody @Valid Transference transference) {
        accountService.transferBalance(transference);
    }
}
