package com.ironhack.Midterm.Project.controller.account.interfaces;

import com.ironhack.Midterm.Project.model.accounts.Checking;
import com.ironhack.Midterm.Project.model.accounts.Money;
import com.ironhack.Midterm.Project.model.users.User;

import java.util.List;
import java.util.Optional;

public interface CheckingController {
    List<Checking> findAll();
    Checking findById(Long id);
    Checking findByUser(User user);
    Checking store(Checking checking);
    void update(Long id, Checking checking);
    void updateBalance(Long id, Money balance);
    void delete(Long id);

}
