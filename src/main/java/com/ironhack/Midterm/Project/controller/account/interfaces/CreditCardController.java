package com.ironhack.Midterm.Project.controller.account.interfaces;

import com.ironhack.Midterm.Project.controller.account.dto.CreditCardDTO;
import com.ironhack.Midterm.Project.controller.account.dto.MoneyDTO;
import com.ironhack.Midterm.Project.model.accounts.CreditCard;
import com.ironhack.Midterm.Project.model.users.User;

import java.util.List;

public interface CreditCardController {
    List<CreditCard> findAll();
    CreditCard findById(Long id);
    CreditCard findByUser(User user);
    CreditCard store(CreditCardDTO creditCardDto);
    void update(Long id, CreditCard creditCard);
    void updateBalance(Long id, MoneyDTO balance);
    void delete(Long id);
}
