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

    public Optional<Staff> findById(Integer id) {
        return staffRepository.findById(id);
    }

    public Staff updateStaff(Integer id, Staff staff) {
        Optional<Staff> staffById = staffRepository.findById(id);

        Staff staff1 = staffById.get();
        staff1.setFirstname(staff.getFirstname());
        staff1.setLastname(staff.getLastname());
        staff1.setEmail(staff.getEmail());
        return staffRepository.save(staff1);
    }

    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    public void deleteById(Integer id) {
        staffRepository.deleteById(id);
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

    public List<Staff> findByFirstName(String firstname) {
        return staffRepository.findByfirstname(firstname);
    }

    public List<Staff> findByActiveStaff() {
        return staffRepository.findByactive(true);
    }

    public List<Staff> findByInactiveStaff() {
        return staffRepository.findByactive(false);
    }

    public Staff findByEmail(String email) {
        return staffRepository.findByemail(email);
    }
}
