package com.ironhack.Midterm.Project.controller.account.interfaces;

import com.ironhack.Midterm.Project.controller.account.dto.MoneyDTO;
import com.ironhack.Midterm.Project.model.accounts.Money;
import com.ironhack.Midterm.Project.model.accounts.Saving;
import com.ironhack.Midterm.Project.model.accounts.StudentChecking;
import com.ironhack.Midterm.Project.model.users.User;

import java.util.List;

public interface StudentCheckingController {
    List<StudentChecking> findAll();
    StudentChecking findById(Long id);
   // StudentChecking findByUser(User user);
    StudentChecking store(StudentChecking studentChecking);
    void update(Long id, StudentChecking studentChecking);
    void updateBalance(Long id, MoneyDTO balance);
    void delete(Long id);
}
