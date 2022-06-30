package com.ironhack.Midterm.Project.service.account.impl;

import com.ironhack.Midterm.Project.model.accounts.Checking;
import com.ironhack.Midterm.Project.repositories.accountRepository.CheckingRepository;
import com.ironhack.Midterm.Project.service.account.interfaces.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CheckingServiceImpl implements CheckingService {

    @Autowired
    private CheckingRepository checkingRepository;

    public void update(Long id, Checking checking) {
        Checking checking1 = checkingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        checking.setId(id);
        checkingRepository.save(checking);
    }

    public void updateBalance(Long id, BigDecimal balance) {
        Checking checking = checkingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        checking.getBalance().setAmount(balance);
        checkingRepository.save(checking);
    }
    public void delete(Long id) {
        Checking checking = checkingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        checkingRepository.delete(checking);
    }
}
