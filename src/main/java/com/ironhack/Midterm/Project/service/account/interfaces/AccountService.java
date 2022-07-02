package com.ironhack.Midterm.Project.service.account.interfaces;

import com.ironhack.Midterm.Project.controller.account.dto.Transference;
import com.ironhack.Midterm.Project.controller.user.dto.ThirdPartyDTO;
import com.ironhack.Midterm.Project.model.accounts.Account;
import com.ironhack.Midterm.Project.repositories.accountRepository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.math.BigDecimal;

public interface AccountService {
    void transferBalance(Transference transference);
    Account thirdPartyReceive(ThirdPartyDTO thirdPartyTransactionDTO);
    Account thirdPartySend(ThirdPartyDTO thirdPartyDto);

}
