package com.ironhack.Midterm.Project.service.user.impl;

import com.ironhack.Midterm.Project.model.users.AccountHolder;
import com.ironhack.Midterm.Project.repositories.userRepository.AccountHolderRepository;
import com.ironhack.Midterm.Project.service.user.interfaces.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class AccountHolderServiceImpl implements AccountHolderService {
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    public void update(Long id, AccountHolder accountHolder) {
        AccountHolder accountHolder1 = accountHolderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        accountHolder.setId(id);
        accountHolderRepository.save(accountHolder);
    }
    public void delete(Long id) {
        AccountHolder accountHolder = accountHolderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        accountHolderRepository.delete(accountHolder);
    }
}
