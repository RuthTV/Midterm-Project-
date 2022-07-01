package com.ironhack.Midterm.Project.controller.account.interfaces;

import com.ironhack.Midterm.Project.controller.account.dto.MoneyDTO;
import com.ironhack.Midterm.Project.controller.account.dto.StudentCheckingDTO;
import com.ironhack.Midterm.Project.model.accounts.StudentChecking;

import java.util.List;

public interface StudentCheckingController {
    List<StudentChecking> findAll();
    StudentChecking findById(Long id);
   // StudentChecking findByUser(User user);
    StudentChecking store(StudentCheckingDTO studentCheckingDto);
    void update(Long id, StudentChecking studentChecking);
    void updateBalance(Long id, MoneyDTO balance);
    void delete(Long id);
}
