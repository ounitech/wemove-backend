package com.ounitech.wemove.services;

import com.ounitech.wemove.models.Staff;
import com.ounitech.wemove.repositories.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public Staff save(Staff staff) {
        return staffRepository.save(staff);
    }

    public Staff updateStaff(Integer id, Staff staff) {
        Optional<Staff> staffById = staffRepository.findById(id);

        Staff staff1 = staffById.get();
        staff1.setFirstname(staff.getFirstname());
        staff1.setLastname(staff.getLastname());
        staff1.setEmail(staff.getEmail());
        return staffRepository.save(staff1);
    }

    public Staff activateStaff(Integer id) {
        Optional<Staff> staffById = staffRepository.findById(id);

        Staff staff = staffById.get();
        staff.setActive(true);

        return staffRepository.save(staff);
    }

    public Staff deactivateStaff(Integer id) {
        Optional<Staff> staffById = staffRepository.findById(id);

        Staff staff = staffById.get();
        staff.setActive(false);

        return staffRepository.save(staff);
    }

    public void deleteById(Integer id) {
        staffRepository.deleteById(id);
    }

    public Optional<Staff> findById(Integer id) {
        return staffRepository.findById(id);
    }

    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    public List<Staff> findByFirstName(String firstname) {
        return staffRepository.findByFirstname(firstname);
    }

    public List<Staff> findByActiveStaff() {
        return staffRepository.findByActive(true);
    }

    public List<Staff> findByInactiveStaff() {
        return staffRepository.findByActive(false);
    }

    public Staff findByEmail(String email) {
        return staffRepository.findByEmail(email).orElse(null);
    }
}
