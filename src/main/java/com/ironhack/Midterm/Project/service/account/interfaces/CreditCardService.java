package com.ironhack.Midterm.Project.service.account.interfaces;

import com.ironhack.Midterm.Project.model.accounts.Checking;
import com.ironhack.Midterm.Project.model.accounts.CreditCard;

import java.math.BigDecimal;

public interface CreditCardService {
    void update(Long id, CreditCard creditCard);
    void updateBalance(Long id, BigDecimal balance);
    void delete(Long id);
}
