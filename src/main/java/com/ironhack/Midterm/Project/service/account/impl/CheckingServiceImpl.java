package com.ironhack.Midterm.Project.service.account.impl;

import com.ironhack.Midterm.Project.controller.account.dto.CheckingDTO;
import com.ironhack.Midterm.Project.model.accounts.Checking;
import com.ironhack.Midterm.Project.model.money.Money;
import com.ironhack.Midterm.Project.model.users.AccountHolder;
import com.ironhack.Midterm.Project.repositories.accountRepository.CheckingRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.AccountHolderRepository;
import com.ironhack.Midterm.Project.service.account.interfaces.CheckingService;
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

    public Checking store(CheckingDTO checkingDto){
        AccountHolder primaryUser = accountHolderRepository.findById(checkingDto.getPrimaryUserId1()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        LocalDate today24YearsBefore = LocalDate.now().minusYears(24);
        if(primaryUser.getDateOfBirth().toLocalDate().isBefore(today24YearsBefore)){
            throw new IllegalArgumentException("The primary owner of the Checking must be at least 24 old\n" +
                                                "Please create a Student Checking");
        }
        Optional<AccountHolder> secundaryUser = accountHolderRepository.findById(checkingDto.getSecundaryUserId2());
        Checking checking = new Checking(new Money(checkingDto.getMoney(), Currency.getInstance("USD")), checkingDto.getSecretKey(),
                primaryUser, secundaryUser.get(), checkingDto.getCreationDate());
        return checking;
    }
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
