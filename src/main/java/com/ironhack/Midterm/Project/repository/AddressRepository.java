package com.ironhack.Midterm.Project.repository;

import com.ironhack.Midterm.Project.model.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
