package com.ironhack.Midterm.Project.service.account.impl;

import com.ironhack.Midterm.Project.controller.account.dto.SavingDTO;
import com.ironhack.Midterm.Project.model.money.Money;
import com.ironhack.Midterm.Project.model.accounts.Saving;
import com.ironhack.Midterm.Project.model.users.AccountHolder;
import com.ironhack.Midterm.Project.repositories.accountRepository.SavingRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.AccountHolderRepository;
import com.ironhack.Midterm.Project.service.account.interfaces.SavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;

@Service
public class SavingServiceImpl implements SavingService {
    @Autowired
    private SavingRepository savingRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;
    public Saving store(SavingDTO savingDto){
        AccountHolder primaryUser = accountHolderRepository.findById(savingDto.getPrimaryUserId1()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        Optional<AccountHolder> secundaryUser = accountHolderRepository.findById(savingDto.getSecundaryUserId2());
        Saving saving = new Saving(new Money(savingDto.getMoney(), Currency.getInstance("USD")), savingDto.getSecretKey(),
                primaryUser, secundaryUser.get(), savingDto.getCreationDate(), savingDto.getInterestRate());
        return saving;
    }

    public void update(Long id, Saving saving) {
        Saving saving1 = savingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        saving.setId(id);
        savingRepository.save(saving);
    }

    public void updateBalance(Long id, BigDecimal balance) {
        Saving saving = savingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        saving.getBalance().setAmount(balance);
        savingRepository.save(saving);
    }
    public void delete(Long id) {
        Saving saving = savingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        savingRepository.delete(saving);
    }

}
