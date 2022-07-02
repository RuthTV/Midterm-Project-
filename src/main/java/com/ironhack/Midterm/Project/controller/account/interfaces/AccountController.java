package com.ironhack.Midterm.Project.controller.account.interfaces;

import com.ironhack.Midterm.Project.controller.account.dto.Transference;
import com.ironhack.Midterm.Project.controller.user.dto.ThirdPartyDTO;
import com.ironhack.Midterm.Project.model.accounts.Account;
import com.ironhack.Midterm.Project.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Set;

public interface AccountController {
    void transferBalance(@AuthenticationPrincipal CustomUserDetails userDetails,Transference transference);
    Set<Account> findAllMyAccounts(@AuthenticationPrincipal CustomUserDetails userDetails);
    Account thirdPartyReceive(@PathVariable String hashedKey, ThirdPartyDTO thirdPartyTransactionDTO);
    Account thirdPartySend(@PathVariable String hashedKey, ThirdPartyDTO thirdPartyTransactionDTO);
}
