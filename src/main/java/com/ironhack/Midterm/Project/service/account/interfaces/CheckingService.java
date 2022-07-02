package com.ironhack.Midterm.Project.service.account.interfaces;

import com.ironhack.Midterm.Project.controller.account.dto.CheckingDTO;
import com.ironhack.Midterm.Project.controller.account.dto.MoneyDTO;
import com.ironhack.Midterm.Project.model.accounts.Checking;

import java.math.BigDecimal;

public interface CheckingService {
    Checking store(CheckingDTO checkingDto);
    Checking update(Long id, CheckingDTO checkingDto);
    void updateBalance(Long id, BigDecimal balance);
    void delete(Long id);
}
