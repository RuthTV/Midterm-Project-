package com.ironhack.Midterm.Project.controller.user.interfaces;

import com.ironhack.Midterm.Project.model.users.AccountHolder;
import com.ironhack.Midterm.Project.model.users.Admin;

import java.util.List;

public interface AdminController {
    List<Admin> findAll();
    Admin findById(Long id);
    Admin store(Admin admin);
    void update(Long id, Admin admin);
    void delete(Long id);
}
