package com.ironhack.Midterm.Project.service.account.impl;

import com.ironhack.Midterm.Project.controller.account.dto.Transference;
import com.ironhack.Midterm.Project.model.accounts.Account;
import com.ironhack.Midterm.Project.model.accounts.Checking;
import com.ironhack.Midterm.Project.model.users.AccountHolder;
import com.ironhack.Midterm.Project.repositories.accountRepository.AccountRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.AccountHolderRepository;
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
    @Autowired
    AccountHolderRepository accountHolderRepository;

    public void transferBalance(Transference transference) {
        AccountHolder accountHolder = accountHolderRepository.findByUsername(transference.getName()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        BigDecimal money;
        Account account1 = null;
        for(Account account : accountHolder.getAccounts()){
            if(account.getBalance().getAmount().compareTo(transference.getMoney()) < 0) {
                account1 = account;
                break;
            }else{
                throw new IllegalArgumentException("This Owner has not enough balance");
            }
        }
        Account account2 = accountRepository.findById(transference.getAccountId2()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        account1.getBalance().setAmount((account1.getBalance().getAmount().subtract(transference.getMoney())));
        accountRepository.save(account1);
        account2.getBalance().setAmount(account2.getBalance().getAmount().add(transference.getMoney()));
        accountRepository.save(account2);
    }
}
