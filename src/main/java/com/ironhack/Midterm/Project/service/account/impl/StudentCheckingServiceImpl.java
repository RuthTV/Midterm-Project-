package com.ironhack.Midterm.Project.service.account.impl;

import com.ironhack.Midterm.Project.model.accounts.Saving;
import com.ironhack.Midterm.Project.model.accounts.StudentChecking;
import com.ironhack.Midterm.Project.repositories.accountRepository.StudentCheckingRepository;
import com.ironhack.Midterm.Project.service.account.interfaces.StudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class StudentCheckingServiceImpl implements StudentCheckingService {
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;
    public void update(Long id, StudentChecking studentChecking) {
        StudentChecking studentChecking1 = studentCheckingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        studentChecking.setId(id);
        studentCheckingRepository.save(studentChecking);
    }

    public void updateBalance(Long id, BigDecimal balance) {
        StudentChecking studentChecking = studentCheckingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        studentChecking.getBalance().setBalance(balance);
        studentCheckingRepository.save(studentChecking);
    }
    public void delete(Long id) {
        StudentChecking studentChecking = studentCheckingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        studentCheckingRepository.delete(studentChecking);
    }
}
