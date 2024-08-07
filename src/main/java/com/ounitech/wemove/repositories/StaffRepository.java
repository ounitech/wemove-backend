package com.ounitech.wemove.repositories;

import com.ounitech.wemove.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

    List<Staff> findByfirstname(String firstname);

    List<Staff> findByactive(Byte active);

    Staff findByemail(String email);
}
