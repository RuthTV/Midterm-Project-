package com.ironhack.Midterm.Project.service.account.impl;

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

    public void transferBalance(Long id1, Long id2, BigDecimal balance) {
        Account account1 = accountRepository.findById(id1).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        Account account2 = accountRepository.findById(id1).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        account1.getBalance().setBalance(account1.getBalance().getBalance().subtract(balance));
        accountRepository.save(account1);
        account2.getBalance().setBalance(account2.getBalance().getBalance().add(balance));
        accountRepository.save(account2);
    }
}
