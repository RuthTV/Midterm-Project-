package com.ironhack.Midterm.Project.controller.account.interfaces;

import com.ironhack.Midterm.Project.controller.account.dto.MoneyDTO;
import com.ironhack.Midterm.Project.controller.account.dto.SavingDTO;
import com.ironhack.Midterm.Project.model.accounts.Saving;
import com.ironhack.Midterm.Project.model.users.User;
import com.ironhack.Midterm.Project.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface SavingController {
    List<Saving> findAll(@AuthenticationPrincipal CustomUserDetails userDetails);
    Saving findById(@AuthenticationPrincipal CustomUserDetails userDetails, Long id);
    Saving findByUser(@AuthenticationPrincipal CustomUserDetails userDetails, User user);
    Saving store(@AuthenticationPrincipal CustomUserDetails userDetails, SavingDTO savingDto);
    void update(@AuthenticationPrincipal CustomUserDetails userDetails, Long id, SavingDTO savingDto);
    void updateBalance(@AuthenticationPrincipal CustomUserDetails userDetails, Long id, MoneyDTO balance);
    void delete(@AuthenticationPrincipal CustomUserDetails userDetails, Long id);
}
