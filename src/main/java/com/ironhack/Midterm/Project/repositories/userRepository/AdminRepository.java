package com.ironhack.Midterm.Project.repositories.userRepository;

import com.ironhack.Midterm.Project.model.users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
