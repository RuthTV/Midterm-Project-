package com.ironhack.Midterm.Project.service.account.interfaces;

import com.ironhack.Midterm.Project.repositories.accountRepository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public interface AccountService {
    void transferBalance(Long id1, Long id2, BigDecimal balance);

}
