package com.ironhack.Midterm.Project.controller.account.interfaces;

import com.ironhack.Midterm.Project.controller.account.dto.CreditCardDTO;
import com.ironhack.Midterm.Project.controller.account.dto.MoneyDTO;
import com.ironhack.Midterm.Project.model.accounts.CreditCard;
import com.ironhack.Midterm.Project.model.users.User;
import com.ironhack.Midterm.Project.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface CreditCardController {
    List<CreditCard> findAll(@AuthenticationPrincipal CustomUserDetails userDetails);
    CreditCard findById(@AuthenticationPrincipal CustomUserDetails userDetails,Long id);
    CreditCard findByUser(@AuthenticationPrincipal CustomUserDetails userDetails,User user);
    CreditCard store(@AuthenticationPrincipal CustomUserDetails userDetails,CreditCardDTO creditCardDto);
    void update(@AuthenticationPrincipal CustomUserDetails userDetails,Long id, CreditCardDTO Dto);
    void updateBalance(@AuthenticationPrincipal CustomUserDetails userDetails,Long id, MoneyDTO balance);
    void delete(@AuthenticationPrincipal CustomUserDetails userDetails,Long id);
}
