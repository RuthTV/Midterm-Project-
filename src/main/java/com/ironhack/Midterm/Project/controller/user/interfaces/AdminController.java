package com.ironhack.Midterm.Project.controller.user.interfaces;

import com.ironhack.Midterm.Project.model.users.Admin;
import com.ironhack.Midterm.Project.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface AdminController {
    List<Admin> findAll(@AuthenticationPrincipal CustomUserDetails userDetails);
    Admin findById(@AuthenticationPrincipal CustomUserDetails userDetails, Long id);
    Admin store(@AuthenticationPrincipal CustomUserDetails userDetails, Admin admin);
    void update(@AuthenticationPrincipal CustomUserDetails userDetails, Long id, Admin admin);
    void delete(@AuthenticationPrincipal CustomUserDetails userDetails, Long id);
}
