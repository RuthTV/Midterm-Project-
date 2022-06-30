package com.ironhack.Midterm.Project.service.account.interfaces;

import com.ironhack.Midterm.Project.controller.account.dto.CreditCardDTO;
import com.ironhack.Midterm.Project.model.accounts.Checking;
import com.ironhack.Midterm.Project.model.accounts.CreditCard;

import java.math.BigDecimal;

public interface CreditCardService {
    CreditCard store(CreditCardDTO creditCardDto);
    void update(Long id, CreditCard creditCard);
    void updateBalance(Long id, BigDecimal balance);
    void delete(Long id);
}
