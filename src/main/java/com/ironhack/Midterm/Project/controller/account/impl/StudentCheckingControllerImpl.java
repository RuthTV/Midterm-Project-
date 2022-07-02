package com.ironhack.Midterm.Project.controller.account.impl;

import com.ironhack.Midterm.Project.controller.account.dto.MoneyDTO;
import com.ironhack.Midterm.Project.controller.account.dto.StudentCheckingDTO;
import com.ironhack.Midterm.Project.controller.account.interfaces.StudentCheckingController;
import com.ironhack.Midterm.Project.model.accounts.StudentChecking;
import com.ironhack.Midterm.Project.model.users.User;
import com.ironhack.Midterm.Project.repositories.accountRepository.StudentCheckingRepository;
import com.ironhack.Midterm.Project.security.CustomUserDetails;
import com.ironhack.Midterm.Project.service.account.interfaces.StudentCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentCheckingControllerImpl implements StudentCheckingController {
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    @Autowired
    private StudentCheckingService studentCheckingService;

    @GetMapping("/studentCheckings")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentChecking> findAll(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return studentCheckingRepository.findAll();
    }

    @GetMapping("/studentCheckings/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentChecking findById(@AuthenticationPrincipal CustomUserDetails userDetails,@PathVariable(name = "id") Long id) {
        Optional<StudentChecking> optionalStudentChecking = studentCheckingRepository.findById(id);
        return optionalStudentChecking.get();
    }

    @GetMapping("/studentCheckings/primaryOwner/{primaryOwner}")
    @ResponseStatus(HttpStatus.OK)
    public StudentChecking findByUser(@AuthenticationPrincipal CustomUserDetails userDetails,@PathVariable(name = "primaryOwner") User user) {
        Optional<StudentChecking> optionalStudentChecking = studentCheckingRepository.findByUserid(user.getId());
        return optionalStudentChecking.get();
    }

    @PostMapping("/studentCheckings")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentChecking store(@AuthenticationPrincipal CustomUserDetails userDetails,@RequestBody @Valid StudentCheckingDTO studentCheckingDto) {
        StudentChecking studentChecking = studentCheckingService.store(studentCheckingDto);
        return studentCheckingRepository.save(studentChecking);
    }

    @PutMapping("/studentCheckings/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal CustomUserDetails userDetails,@PathVariable Long id, @RequestBody @Valid StudentCheckingDTO studentCheckingDto) {
        studentCheckingService.update(id, studentCheckingDto);
    }

    @PatchMapping("/studentCheckings/{id}/balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBalance(@AuthenticationPrincipal CustomUserDetails userDetails,@PathVariable Long id, @RequestBody @Valid MoneyDTO balance) {
        studentCheckingService.updateBalance(id, balance.getBalance());
    }

    @DeleteMapping("/studentCheckings/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal CustomUserDetails userDetails,@PathVariable Long id) {
        studentCheckingService.delete(id);
    }
}
