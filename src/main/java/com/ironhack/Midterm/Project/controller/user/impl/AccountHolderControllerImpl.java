package com.ironhack.Midterm.Project.controller.user.impl;

import com.ironhack.Midterm.Project.controller.user.interfaces.AccountHolderController;
import com.ironhack.Midterm.Project.model.users.AccountHolder;
import com.ironhack.Midterm.Project.repositories.userRepository.AccountHolderRepository;
import com.ironhack.Midterm.Project.security.CustomUserDetails;
import com.ironhack.Midterm.Project.service.user.interfaces.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountHolderControllerImpl implements AccountHolderController {
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private AccountHolderService accountHolderService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/accountHolders")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> findAll(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return accountHolderRepository.findAll();
    }

    @GetMapping("/accountHolders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountHolder findById(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable(name = "id") Long id) {
        Optional<AccountHolder> optionalAccountHolder = accountHolderRepository.findById(id);
        String password = passwordEncoder.encode(optionalAccountHolder.get().getPassword());
        return optionalAccountHolder.get();
    }

    @PostMapping("/accountHolders")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder store(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody @Valid AccountHolder accountHolder) {
        return accountHolderRepository.save(accountHolder);
    }

    @PutMapping("/accountHolders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id, @RequestBody @Valid AccountHolder accountHolder) {
        accountHolderService.update(id, accountHolder);
    }
    @DeleteMapping("/accountHolders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id) {
        accountHolderService.delete(id);
    }

}
