package com.ironhack.Midterm.Project.controller.account.impl;

import com.ironhack.Midterm.Project.controller.account.dto.MoneyDTO;
import com.ironhack.Midterm.Project.controller.account.dto.SavingDTO;
import com.ironhack.Midterm.Project.controller.account.interfaces.SavingController;
import com.ironhack.Midterm.Project.model.accounts.Saving;
import com.ironhack.Midterm.Project.model.users.User;
import com.ironhack.Midterm.Project.repositories.accountRepository.SavingRepository;
import com.ironhack.Midterm.Project.security.CustomUserDetails;
import com.ironhack.Midterm.Project.service.account.interfaces.SavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class SavingControllerImpl implements SavingController {
    @Autowired
    private SavingRepository savingRepository;

    @Autowired
    private SavingService savingService;

    @GetMapping("/savings")
    @ResponseStatus(HttpStatus.OK)
    public List<Saving> findAll(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return savingRepository.findAll();
    }

    @GetMapping("/savings/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Saving findById(@AuthenticationPrincipal CustomUserDetails userDetails,@PathVariable(name = "id") Long id) {
        Optional<Saving> optionalSaving = savingRepository.findById(id);
        return optionalSaving.get();
    }

    @GetMapping("/savings/primaryOwner/{primaryOwner}")
    @ResponseStatus(HttpStatus.OK)
    public Saving findByUser(@AuthenticationPrincipal CustomUserDetails userDetails,@PathVariable(name = "primaryOwner") User user) {
        Optional<Saving> optionalSaving = savingRepository.findByUserid(user.getId());
        return optionalSaving.get();
    }

    @PostMapping("/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Saving store(@AuthenticationPrincipal CustomUserDetails userDetails,@RequestBody @Valid SavingDTO savingDto) {
        Saving saving =  savingService.store(savingDto);
        return savingRepository.save(saving);
    }

    @PutMapping("/savings/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal CustomUserDetails userDetails,@PathVariable Long id, @RequestBody @Valid SavingDTO savingDto) {
        savingService.update(id, savingDto);
    }

    @PatchMapping("/savings/{id}/balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBalance(@AuthenticationPrincipal CustomUserDetails userDetails,@PathVariable Long id, @RequestBody @Valid MoneyDTO balance) {
        savingService.updateBalance(id, balance.getBalance());
    }

    @DeleteMapping("/savings/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id) {
        savingService.delete(id);
    }
}
