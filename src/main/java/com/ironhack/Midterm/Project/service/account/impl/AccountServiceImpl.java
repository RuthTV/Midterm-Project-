package com.ironhack.Midterm.Project.service.account.impl;

import com.ironhack.Midterm.Project.controller.account.dto.Transference;
import com.ironhack.Midterm.Project.model.accounts.Account;
import com.ironhack.Midterm.Project.model.accounts.Checking;
import com.ironhack.Midterm.Project.repositories.accountRepository.AccountRepository;
import com.ironhack.Midterm.Project.service.account.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    public void transferBalance(Transference transference) {
        Account account1 = accountRepository.findById(transference.getAccountId1()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        Account account2 = accountRepository.findById(transference.getAccountId2()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        account1.getBalance().setAmount((account1.getBalance().getAmount().subtract(transference.getMoney().getAmount())));
        accountRepository.save(account1);
        account2.getBalance().setAmount(account2.getBalance().getAmount().add(transference.getMoney().getAmount()));
        accountRepository.save(account2);
    }
}
