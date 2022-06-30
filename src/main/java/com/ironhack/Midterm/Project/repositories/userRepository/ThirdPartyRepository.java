package com.ironhack.Midterm.Project.repositories.userRepository;

import com.ironhack.Midterm.Project.model.users.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyRepository extends JpaRepository<ThirdParty, Long> {
}
