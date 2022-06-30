package com.ironhack.Midterm.Project.controller.account.interfaces;

import com.ironhack.Midterm.Project.model.accounts.Checking;
import com.ironhack.Midterm.Project.model.accounts.CreditCard;
import com.ironhack.Midterm.Project.model.accounts.Money;
import com.ironhack.Midterm.Project.model.users.User;

import java.util.List;

public interface CreditCardController {
    List<CreditCard> findAll();
    CreditCard findById(Long id);
 //   CreditCard findByUser(User user);
    CreditCard store(CreditCard creditCard);
    void update(Long id, CreditCard creditCard);
    void updateBalance(Long id, Money balance);
    void delete(Long id);
}
