package com.ironhack.Midterm.Project.repositories.userRepository;

import com.ironhack.Midterm.Project.model.users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {
}
