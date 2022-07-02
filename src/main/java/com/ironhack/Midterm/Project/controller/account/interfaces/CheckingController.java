package com.ironhack.Midterm.Project.controller.account.interfaces;

import com.ironhack.Midterm.Project.controller.account.dto.CheckingDTO;
import com.ironhack.Midterm.Project.controller.account.dto.MoneyDTO;
import com.ironhack.Midterm.Project.model.accounts.Checking;
import com.ironhack.Midterm.Project.model.users.User;
import com.ironhack.Midterm.Project.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface CheckingController {
    List<Checking> findAll(@AuthenticationPrincipal CustomUserDetails userDetails);
    Checking findById(@AuthenticationPrincipal CustomUserDetails userDetails,Long id);
    Checking store(@AuthenticationPrincipal CustomUserDetails userDetails,CheckingDTO checkingDto);
    void update(@AuthenticationPrincipal CustomUserDetails userDetails,Long id, CheckingDTO checkingDto);
    void updateBalance(@AuthenticationPrincipal CustomUserDetails userDetails,Long id, MoneyDTO balance);
    void delete(@AuthenticationPrincipal CustomUserDetails userDetails,Long id);

}
