package com.ironhack.Midterm.Project.controller.user.interfaces;

import com.ironhack.Midterm.Project.model.users.AccountHolder;
import com.ironhack.Midterm.Project.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface AccountHolderController {
    List<AccountHolder> findAll(@AuthenticationPrincipal CustomUserDetails userDetails);
    AccountHolder findById(@AuthenticationPrincipal CustomUserDetails userDetails,Long id);
    AccountHolder store(@AuthenticationPrincipal CustomUserDetails userDetails, AccountHolder accountHolder);
    void update(@AuthenticationPrincipal CustomUserDetails userDetails, Long id, AccountHolder accountHolder);
    void delete(@AuthenticationPrincipal CustomUserDetails userDetails,Long id);
}
