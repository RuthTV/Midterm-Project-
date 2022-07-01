package com.ironhack.Midterm.Project.controller.user.impl;

import com.ironhack.Midterm.Project.controller.user.interfaces.AdminController;
import com.ironhack.Midterm.Project.model.users.Admin;
import com.ironhack.Midterm.Project.repositories.userRepository.AdminRepository;
import com.ironhack.Midterm.Project.service.user.interfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class AdminControllerImpl implements AdminController {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminService adminService;

    @GetMapping("/admins")
    @ResponseStatus(HttpStatus.OK)
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @GetMapping("/admins/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Admin findById(@PathVariable(name = "id") Long id) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        return optionalAdmin.get();
    }

    @PostMapping("/admins")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin store(@RequestBody @Valid Admin admin) {
        return adminRepository.save(admin);
    }

    @PutMapping("/admins/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody @Valid Admin admin) {
        adminService.update(id, admin);
    }
    @DeleteMapping("/admins/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        adminService.delete(id);
    }
}
