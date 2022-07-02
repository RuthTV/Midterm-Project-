package com.ironhack.Midterm.Project.service.account.impl;

import com.ironhack.Midterm.Project.controller.account.dto.CheckingDTO;
import com.ironhack.Midterm.Project.model.accounts.Checking;
import com.ironhack.Midterm.Project.model.money.Money;
import com.ironhack.Midterm.Project.model.users.AccountHolder;
import com.ironhack.Midterm.Project.repositories.accountRepository.CheckingRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.AccountHolderRepository;
import com.ironhack.Midterm.Project.service.account.interfaces.CheckingService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Optional;

@Service
public class CheckingServiceImpl implements CheckingService {

    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Checking store(CheckingDTO checkingDto){
        AccountHolder primaryUser = accountHolderRepository.findById(checkingDto.getPrimaryUserId1()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "AccountHolder not found"));
        LocalDate today24YearsBefore = LocalDate.now().minusYears(24);
        checkingDto.setSecretKey(passwordEncoder.encode(checkingDto.getSecretKey()));
        if(primaryUser.getDateOfBirth().toLocalDate().isAfter(today24YearsBefore)){
            throw new IllegalArgumentException("The primary owner of the Checking must be at least 24 old. " +
                                                "Please create a Student Checking");
        }
        Long secundaryUserId2 = checkingDto.getSecundaryUserId2();
        if (secundaryUserId2 == 0) {
            Checking checking = new Checking(new Money(checkingDto.getMoney(), Currency.getInstance("USD")), checkingDto.getSecretKey(),
                    primaryUser, checkingDto.getCreationDate());
            return checking;
        }
        Optional<AccountHolder> secundaryUser = accountHolderRepository.findById(checkingDto.getSecundaryUserId2());
        Checking checking = new Checking(new Money(checkingDto.getMoney(), Currency.getInstance("USD")), checkingDto.getSecretKey(),
                    primaryUser, secundaryUser.get(), checkingDto.getCreationDate());
        return checking;
    }
    public Checking update(Long id, CheckingDTO checkingDto) {
        Checking checking1 = checkingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        Optional<AccountHolder> primaryUser = accountHolderRepository.findById(checkingDto.getPrimaryUserId1());
        checkingDto.setSecretKey(passwordEncoder.encode(checkingDto.getSecretKey()));
        if (checkingDto.getSecundaryUserId2() == 0) {
            Checking checking = new Checking(new Money(checkingDto.getMoney(), Currency.getInstance("USD")), checkingDto.getSecretKey(),
                    primaryUser.get(), checkingDto.getCreationDate());
            return checking;
        }
        Optional<AccountHolder> secundaryUser = accountHolderRepository.findById(checkingDto.getSecundaryUserId2());
        Checking checking = new Checking(new Money(checkingDto.getMoney(), Currency.getInstance("USD")), checkingDto.getSecretKey(),
                primaryUser.get(), secundaryUser.get(), checkingDto.getCreationDate());
        checkingRepository.save(checking);
        return checking;
    }

    public void updateBalance(Long id, BigDecimal balance) {
        Checking checking = checkingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        checking.getBalance(checking.getLastActualizedDate()).setAmount(balance);
        checkingRepository.save(checking);
    }
    public void delete(Long id) {
        Checking checking = checkingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        checkingRepository.delete(checking);
    }
}
