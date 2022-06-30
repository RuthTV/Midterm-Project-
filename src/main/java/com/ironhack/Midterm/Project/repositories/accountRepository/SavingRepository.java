package com.ironhack.Midterm.Project.repositories.accountRepository;

import com.ironhack.Midterm.Project.model.accounts.CreditCard;
import com.ironhack.Midterm.Project.model.accounts.Saving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavingRepository extends JpaRepository<Saving, Long> {
    @Query("SELECT s FROM Saving s JOIN User u ON s.primaryOwner = u.id WHERE u.id = :id")
    Optional<Saving> findByUserid(@Param("id") Long id);
}
