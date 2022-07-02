package com.ironhack.Midterm.Project.service.account.interfaces;

import com.ironhack.Midterm.Project.controller.account.dto.SavingDTO;
import com.ironhack.Midterm.Project.model.accounts.CreditCard;
import com.ironhack.Midterm.Project.model.accounts.Saving;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


public interface SavingService {
    Saving store(SavingDTO savingDto);
    void update(Long id, SavingDTO savingDto);
    void updateBalance(Long id, BigDecimal balance);
    void delete(Long id);
}
