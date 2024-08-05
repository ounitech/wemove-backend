package com.ounitech.wemove.repositories;

import com.ounitech.wemove.models.Member;
import com.ounitech.wemove.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    public List<Staff> findByfirstname(String firstname);

    public List<Staff> findByactive(Byte active);

    public Staff findByemail(String email);
}
