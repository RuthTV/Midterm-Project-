package com.ironhack.Midterm.Project.service.account.impl;

import com.ironhack.Midterm.Project.model.accounts.Checking;
import com.ironhack.Midterm.Project.model.accounts.CreditCard;
import com.ironhack.Midterm.Project.repositories.accountRepository.CreditCardRepository;
import com.ironhack.Midterm.Project.service.account.interfaces.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class CreditCardServiceImpl implements CreditCardService {
    @Autowired
    private CreditCardRepository creditCardRepository;

    public void update(Long id, CreditCard creditCard) {
        CreditCard creditCard1 = creditCardRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        creditCard.setId(id);
        creditCardRepository.save(creditCard);
    }

    public void updateBalance(Long id, BigDecimal balance) {
        CreditCard creditCard = creditCardRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        creditCard.getBalance().setAmount(balance);
        creditCardRepository.save(creditCard);
    }
    public void delete(Long id) {
        CreditCard creditCard = creditCardRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        creditCardRepository.delete(creditCard);
    }

}
