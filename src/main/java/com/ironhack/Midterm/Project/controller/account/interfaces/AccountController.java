package com.ironhack.Midterm.Project.controller.account.interfaces;

import com.ironhack.Midterm.Project.model.accounts.Money;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AccountController {
    void transferBalance(Long id1, Long id2, Money balance);
}
