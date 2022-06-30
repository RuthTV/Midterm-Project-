package com.ironhack.Midterm.Project.repositories.accountRepository;

import com.ironhack.Midterm.Project.model.accounts.Checking;
import com.ironhack.Midterm.Project.model.accounts.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    @Query("SELECT cd FROM CreditCard cd JOIN User u ON cd.primaryOwner = u.id WHERE u.id = :id")
    Optional<CreditCard> findByUserid(@Param("id") Long id);
}
