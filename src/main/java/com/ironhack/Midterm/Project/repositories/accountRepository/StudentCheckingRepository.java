package com.ironhack.Midterm.Project.repositories.accountRepository;

import com.ironhack.Midterm.Project.model.accounts.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentCheckingRepository extends JpaRepository<StudentChecking, Long> {
    @Query("SELECT sc FROM StudentChecking sc JOIN User u ON sc.primaryOwner = u.id WHERE u.id = :id")
    Optional<StudentChecking> findByUserid(@Param("id") Long id);
}
