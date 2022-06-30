package com.ironhack.Midterm.Project.controller.user.interfaces;

import com.ironhack.Midterm.Project.model.accounts.Money;
import com.ironhack.Midterm.Project.model.accounts.StudentChecking;
import com.ironhack.Midterm.Project.model.users.AccountHolder;
import com.ironhack.Midterm.Project.model.users.User;

import java.util.List;

public interface AccountHolderController {
    List<AccountHolder> findAll();
    AccountHolder findById(Long id);
    AccountHolder store(AccountHolder accountHolder);
    void update(Long id, AccountHolder accountHolder);
    void delete(Long id);
}
