package com.ironhack.Midterm.Project.service.account.interfaces;

import com.ironhack.Midterm.Project.controller.account.dto.StudentCheckingDTO;
import com.ironhack.Midterm.Project.model.accounts.Saving;
import com.ironhack.Midterm.Project.model.accounts.StudentChecking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface StudentCheckingService {
    StudentChecking store(StudentCheckingDTO studentCheckingDto);
    void update(Long id, StudentCheckingDTO studentCheckingDto);
    void updateBalance(Long id, BigDecimal balance);
    void delete(Long id);

}
