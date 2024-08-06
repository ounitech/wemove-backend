package com.ounitech.wemove.controllers;


import com.ounitech.wemove.models.Staff;
import com.ounitech.wemove.services.StaffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Staff> findById(@PathVariable("id") Integer id) {
        Optional<Staff> staff = staffService.findById(id);

        if (staff.isPresent()) {
            return new ResponseEntity<>(staff.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Staff>> findAllStaff() {
        List<Staff> staff = staffService.findAll();
        if (staff.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }

    @GetMapping("/findByFirstName/{firstname}")
    public ResponseEntity<List<Staff>> findByFirstName(@PathVariable("firstname") String firstname) {
        List<Staff> staff = staffService.findByFirstName(firstname);
        if (staff.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }

    @GetMapping("/findByActive")
    public ResponseEntity<List<Staff>> findByActiveStaff() {
        List<Staff> staff = staffService.findByActiveStaff();
        if (staff.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }

    @GetMapping("/findByInactive")
    public ResponseEntity<List<Staff>> findByInactiveStaff() {
        List<Staff> staff = staffService.findByInactiveStaff();
        if (staff.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<Staff> findByEmail(@PathVariable("email") String email) {
        Staff staff = staffService.findByEmail(email);
        if (staff == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Staff> save(@RequestBody Staff input) {
        //ensure that the email entered is unique
        if (staffService.findByEmail(input.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        if (input.getEmail() == null
                || input.getFirstname() == null
                || input.getLastname() == null
                || input.getAddress() == null
                || input.getPhone() == null
                || input.getPicture() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (input.getEmail().isEmpty()
                || input.getFirstname().isEmpty()
                || input.getLastname().isEmpty()
                || input.getAddress().isEmpty()
                || input.getPhone().isEmpty()
                || input.getPicture().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Staff savedStaff = staffService.save(input);
        return new ResponseEntity<>(savedStaff, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable("id") Integer id, @RequestBody Staff input) {
        Optional<Staff> staff = staffService.findById(id);

        if (staff.isPresent()) {
            if (input.getEmail() == null
                    || input.getFirstname() == null
                    || input.getLastname() == null
                    || input.getAddress() == null
                    || input.getPhone() == null
                    || input.getPicture() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (input.getEmail().isEmpty()
                    || input.getFirstname().isEmpty()
                    || input.getLastname().isEmpty()
                    || input.getAddress().isEmpty()
                    || input.getPhone().isEmpty()
                    || input.getPicture().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Staff updatedStaff = staffService.updateStaff(id, input);
            return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<Staff> activateStaff(@PathVariable("id") Integer id) {
        Optional<Staff> staff = staffService.findById(id);

        if (staff.isPresent()) {
            Staff activatedStaff = staffService.activateStaff(id);
            return new ResponseEntity<>(activatedStaff, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<Staff> deactivateStaff(@PathVariable("id") Integer id) {
        Optional<Staff> staff = staffService.findById(id);

        if (staff.isPresent()) {
            Staff deactivatedStaff = staffService.deactivateStaff(id);
            return new ResponseEntity<>(deactivatedStaff, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Optional<Staff> staff = staffService.findById(id);

        if (staff.isPresent()) {
            staffService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
