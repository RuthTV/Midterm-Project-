package com.ironhack.Midterm.Project.controller.account.impl;

import com.ironhack.Midterm.Project.controller.account.dto.Transference;
import com.ironhack.Midterm.Project.controller.account.interfaces.AccountController;
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

    @PutMapping("/transference")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transferBalance(@RequestBody @Valid Transference transference) {
        accountService.transferBalance(transference);
    }
}
