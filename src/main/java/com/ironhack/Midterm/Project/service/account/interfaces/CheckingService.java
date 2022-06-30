package com.ironhack.Midterm.Project.service.account.interfaces;

import com.ironhack.Midterm.Project.model.accounts.Checking;
import com.ironhack.Midterm.Project.model.accounts.Money;

import java.math.BigDecimal;

public interface CheckingService {
    void update(Long id, Checking checking);
    void updateBalance(Long id, BigDecimal balance);
    void delete(Long id);
}
