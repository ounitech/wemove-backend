package com.ounitech.wemove.repositories;

import com.ounitech.wemove.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

    List<Staff> findByFirstname(String firstname);

    List<Staff> findByActive(boolean active);

    Optional<Staff> findByEmail(String email);
}
