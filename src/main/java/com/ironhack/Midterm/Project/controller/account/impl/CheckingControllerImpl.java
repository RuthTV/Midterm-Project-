package com.ironhack.Midterm.Project.controller.account.impl;

import com.ironhack.Midterm.Project.controller.account.dto.CheckingDTO;
import com.ironhack.Midterm.Project.controller.account.dto.MoneyDTO;
import com.ironhack.Midterm.Project.controller.account.interfaces.CheckingController;
import com.ironhack.Midterm.Project.model.accounts.Account;
import com.ironhack.Midterm.Project.model.accounts.Checking;
import com.ironhack.Midterm.Project.model.accounts.Saving;
import com.ironhack.Midterm.Project.model.users.User;
import com.ironhack.Midterm.Project.repositories.accountRepository.CheckingRepository;
import com.ironhack.Midterm.Project.security.CustomUserDetails;
import com.ironhack.Midterm.Project.service.account.interfaces.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class CheckingControllerImpl implements CheckingController {
    @Autowired
    private CheckingRepository checkingRepository;

    @Autowired
    private CheckingService checkingService;

    @GetMapping("/checkings")
    @ResponseStatus(HttpStatus.OK)
    public List<Checking> findAll(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return checkingRepository.findAll();
    }

    @GetMapping("/checkings/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Checking findById(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable(name = "id") Long id) {
        Optional<Checking> optionalChecking = checkingRepository.findById(id);
        return optionalChecking.get();
    }

    @GetMapping("/checkings/primaryOwner/{primaryOwner}")
    @ResponseStatus(HttpStatus.OK)
    public Checking findByUser(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable(name = "primaryOwner") User user) {
        Optional<Checking> optionalChecking = checkingRepository.findByUserid(user.getId());
        return optionalChecking.get();
    }

    @PostMapping("/checkings")
    @ResponseStatus(HttpStatus.CREATED)
    public Checking store(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody @Valid CheckingDTO checkingDto) {
        Checking checking = checkingService.store(checkingDto);
        return checkingRepository.save(checking);
    }


    @PutMapping("/checkings/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal CustomUserDetails userDetails,@PathVariable Long id, @RequestBody @Valid CheckingDTO checkingDto) {
        checkingService.update(id, checkingDto);
    }

    @PatchMapping("/checkings/{id}/balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBalance(@AuthenticationPrincipal CustomUserDetails userDetails,@PathVariable Long id, @RequestBody @Valid MoneyDTO balance) {
        checkingService.updateBalance(id, balance.getBalance());
    }

    @DeleteMapping("/checkings/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@AuthenticationPrincipal CustomUserDetails userDetails,@PathVariable Long id) {
        checkingRepository.delete(checkingService.delete(id));;
    }
}
