package com.ironhack.Midterm.Project.service.user.interfaces;


import com.ironhack.Midterm.Project.model.users.AccountHolder;


public interface AccountHolderService {
    void update(Long id, AccountHolder accountHolder);
    void delete(Long id);
}
