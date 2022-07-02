package com.ironhack.Midterm.Project.controller.account.impl;

import com.ironhack.Midterm.Project.controller.account.dto.CreditCardDTO;
import com.ironhack.Midterm.Project.controller.account.dto.MoneyDTO;
import com.ironhack.Midterm.Project.controller.account.interfaces.CreditCardController;
import com.ironhack.Midterm.Project.model.accounts.CreditCard;
import com.ironhack.Midterm.Project.model.users.User;
import com.ironhack.Midterm.Project.repositories.accountRepository.CreditCardRepository;
import com.ironhack.Midterm.Project.security.CustomUserDetails;
import com.ironhack.Midterm.Project.service.account.interfaces.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CreditCardControllerImpl implements CreditCardController {
    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private CreditCardService creditCardService;

    @GetMapping("/creditCards")
    @ResponseStatus(HttpStatus.OK)
    public List<CreditCard> findAll(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return creditCardRepository.findAll();
    }

    @GetMapping("/creditCards/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CreditCard findById(@AuthenticationPrincipal CustomUserDetails userDetails,@PathVariable(name = "id") Long id) {
        Optional<CreditCard> optionalCreditCard = creditCardRepository.findById(id);
        return optionalCreditCard.get();
    }

    @GetMapping("/creditCards/primaryOwner/{primaryOwner}")
    @ResponseStatus(HttpStatus.OK)
    public CreditCard findByUser(@AuthenticationPrincipal CustomUserDetails userDetails,@PathVariable(name = "primaryOwner") User user) {
        Optional<CreditCard> optionalCreditCard = creditCardRepository.findByUserid(user.getId());
        return optionalCreditCard.get();
    }

    @PostMapping("/creditCards")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard store(@AuthenticationPrincipal CustomUserDetails userDetails,@RequestBody @Valid CreditCardDTO creditCardDto) {
        CreditCard creditCard = creditCardService.store(creditCardDto);
        return creditCardRepository.save(creditCard);
    }

    @PutMapping("/creditCards/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal CustomUserDetails userDetails,@PathVariable Long id, @RequestBody @Valid CreditCardDTO creditCardDto) {
        creditCardService.update(id, creditCardDto);
    }

    @PatchMapping("/creditCards/{id}/balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBalance(@AuthenticationPrincipal CustomUserDetails userDetails,@PathVariable Long id, @RequestBody @Valid MoneyDTO balance) {
        creditCardService.updateBalance(id, balance.getBalance());
    }

    @DeleteMapping("/creditCards/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal CustomUserDetails userDetails,@PathVariable Long id) {
        creditCardService.delete(id);
    }
}
