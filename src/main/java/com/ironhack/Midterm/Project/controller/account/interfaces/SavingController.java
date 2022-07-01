package com.ironhack.Midterm.Project.controller.account.interfaces;

import com.ironhack.Midterm.Project.controller.account.dto.MoneyDTO;
import com.ironhack.Midterm.Project.controller.account.dto.SavingDTO;
import com.ironhack.Midterm.Project.model.accounts.Saving;

import java.util.List;

public interface SavingController {
    List<Saving> findAll();
    Saving findById(Long id);
  //  Saving findByUser(User user);
    Saving store(SavingDTO savingDto);
    void update(Long id, Saving saving);
    void updateBalance(Long id, MoneyDTO balance);
    void delete(Long id);
}
