package com.ironhack.Midterm.Project.controller.account.interfaces;

import com.ironhack.Midterm.Project.controller.account.dto.MoneyDTO;
import com.ironhack.Midterm.Project.model.accounts.CreditCard;
import com.ironhack.Midterm.Project.model.accounts.Money;
import com.ironhack.Midterm.Project.model.accounts.Saving;
import com.ironhack.Midterm.Project.model.users.User;

import java.util.List;

public interface SavingController {
    List<Saving> findAll();
    Saving findById(Long id);
  //  Saving findByUser(User user);
    Saving store(Saving saving);
    void update(Long id, Saving saving);
    void updateBalance(Long id, MoneyDTO balance);
    void delete(Long id);
}
