package com.ironhack.Midterm.Project.service.user.interfaces;

import com.ironhack.Midterm.Project.model.users.AccountHolder;
import com.ironhack.Midterm.Project.model.users.Admin;

public interface AdminService {
    void update(Long id, Admin admin);
    void delete(Long id);
}
