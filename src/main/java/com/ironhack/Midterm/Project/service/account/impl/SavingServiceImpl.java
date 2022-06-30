package com.ironhack.Midterm.Project.service.account.impl;

import com.ironhack.Midterm.Project.model.accounts.CreditCard;
import com.ironhack.Midterm.Project.model.accounts.Saving;
import com.ironhack.Midterm.Project.repositories.accountRepository.SavingRepository;
import com.ironhack.Midterm.Project.service.account.interfaces.SavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class SavingServiceImpl implements SavingService {
    @Autowired
    private SavingRepository savingRepository;

    public void update(Long id, Saving saving) {
        Saving saving1 = savingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        saving.setId(id);
        savingRepository.save(saving);
    }

    public void updateBalance(Long id, BigDecimal balance) {
        Saving saving = savingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        saving.getBalance().setBalance(balance);
        savingRepository.save(saving);
    }
    public void delete(Long id) {
        Saving saving = savingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        savingRepository.delete(saving);
    }

}
