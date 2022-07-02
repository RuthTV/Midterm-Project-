package com.ironhack.Midterm.Project.repositories.accountRepository;

import com.ironhack.Midterm.Project.model.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a JOIN AccountHolder ah ON a.primaryOwner = ah.id")
    Set<Account> findMyAccounts(@Param("id") Long id);
}
