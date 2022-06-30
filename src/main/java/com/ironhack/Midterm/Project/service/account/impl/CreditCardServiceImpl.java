package com.ironhack.Midterm.Project.service.account.impl;

import com.ironhack.Midterm.Project.controller.account.dto.CreditCardDTO;
import com.ironhack.Midterm.Project.model.accounts.Checking;
import com.ironhack.Midterm.Project.model.accounts.CreditCard;
import com.ironhack.Midterm.Project.model.accounts.Money;
import com.ironhack.Midterm.Project.model.users.AccountHolder;
import com.ironhack.Midterm.Project.repositories.accountRepository.CreditCardRepository;
import com.ironhack.Midterm.Project.repositories.userRepository.AccountHolderRepository;
import com.ironhack.Midterm.Project.service.account.interfaces.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;

@Service
public class CreditCardServiceImpl implements CreditCardService {
    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    public CreditCard store(CreditCardDTO creditCardDto){
        AccountHolder primaryUser = accountHolderRepository.findById(creditCardDto.getPrimaryUserId1()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        Optional<AccountHolder> secundaryUser = accountHolderRepository.findById(creditCardDto.getSecundaryUserId2());
        CreditCard creditCard = new CreditCard(new Money(creditCardDto.getMoney(), Currency.getInstance("USD")), creditCardDto.getSecretKey(), primaryUser, secundaryUser.get(), creditCardDto.getInterestRate(),
                creditCardDto.getCreationDate(), new Money(creditCardDto.getCreditLimit(), Currency.getInstance("USD")));
        return creditCard;
    }

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
