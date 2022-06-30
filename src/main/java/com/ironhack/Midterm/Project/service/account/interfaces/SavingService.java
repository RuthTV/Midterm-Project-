package com.ironhack.Midterm.Project.service.account.interfaces;

import com.ironhack.Midterm.Project.model.accounts.CreditCard;
import com.ironhack.Midterm.Project.model.accounts.Saving;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


public interface SavingService {
    void update(Long id, Saving saving);
    void updateBalance(Long id, BigDecimal balance);
    void delete(Long id);
}
