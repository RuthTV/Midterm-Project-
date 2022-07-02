package com.ironhack.Midterm.Project.service.account.impl;

import com.ironhack.Midterm.Project.controller.account.dto.StudentCheckingDTO;
import com.ironhack.Midterm.Project.model.accounts.CreditCard;
import com.ironhack.Midterm.Project.model.money.Money;
import com.ironhack.Midterm.Project.model.accounts.StudentChecking;
import com.ironhack.Midterm.Project.model.users.AccountHolder;
import com.ironhack.Midterm.Project.repositories.accountRepository.StudentCheckingRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.AccountHolderRepository;
import com.ironhack.Midterm.Project.service.account.interfaces.StudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Optional;

@Service
public class StudentCheckingServiceImpl implements StudentCheckingService {
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public StudentChecking store(StudentCheckingDTO studentCheckingDto){
        AccountHolder primaryUser = accountHolderRepository.findById(studentCheckingDto.getPrimaryUserId1()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StudentChecking not found"));
        studentCheckingDto.setSecretKey(passwordEncoder.encode(studentCheckingDto.getSecretKey()));
        LocalDate today24YearsBefore = LocalDate.now().minusYears(24);
        if(primaryUser.getDateOfBirth().toLocalDate().isBefore(today24YearsBefore)){
            throw new IllegalArgumentException("The primary owner of the Student Checking must be less than 24 old\n" +
                                                    "Please create a Checking");
        }
        if (studentCheckingDto.getSecundaryUserId2() == 0L) {
            StudentChecking studentChecking = new StudentChecking(new Money(studentCheckingDto.getMoney(), Currency.getInstance("USD")), studentCheckingDto.getSecretKey(),
                    primaryUser, studentCheckingDto.getCreationDate());
            return studentChecking;
        }
        Optional<AccountHolder> secundaryUser = accountHolderRepository.findById(studentCheckingDto.getSecundaryUserId2());
        StudentChecking studentChecking = new StudentChecking(new Money(studentCheckingDto.getMoney(), Currency.getInstance("USD")), studentCheckingDto.getSecretKey(),
                primaryUser, secundaryUser.get(), studentCheckingDto.getCreationDate());
        return studentChecking;
    }
    public void update(Long id, StudentCheckingDTO studentCheckingDto) {
        StudentChecking studentChecking1 = studentCheckingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StudentChecking not found"));
        studentCheckingDto.setSecretKey(passwordEncoder.encode(studentCheckingDto.getSecretKey()));
        Optional<AccountHolder> primaryUser = accountHolderRepository.findById(studentCheckingDto.getPrimaryUserId1());
        if (studentCheckingDto.getSecundaryUserId2() == 0L) {
            StudentChecking studentChecking = new StudentChecking(new Money(studentCheckingDto.getMoney(), Currency.getInstance("USD")), studentCheckingDto.getSecretKey(),
                    primaryUser.get(), studentCheckingDto.getCreationDate());
            studentCheckingRepository.save(studentChecking);
        }
        Optional<AccountHolder> secundaryUser = accountHolderRepository.findById(studentCheckingDto.getSecundaryUserId2());
        StudentChecking studentChecking = new StudentChecking(new Money(studentCheckingDto.getMoney(), Currency.getInstance("USD")), studentCheckingDto.getSecretKey(),
                primaryUser.get(), secundaryUser.get(), studentCheckingDto.getCreationDate());
        studentCheckingRepository.save(studentChecking);
    }

    public void updateBalance(Long id, BigDecimal balance) {
        StudentChecking studentChecking = studentCheckingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StudentChecking not found"));
        studentChecking.getBalance().setAmount(balance);
        studentCheckingRepository.save(studentChecking);
    }
    public void delete(Long id) {
        StudentChecking studentChecking = studentCheckingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StudentChecking not found"));
        studentCheckingRepository.delete(studentChecking);
    }
}
