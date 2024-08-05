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
    public ResponseEntity<Staff> findById(
            @PathVariable("id") Integer id
    ) {
        Optional<Staff> staff = staffService.findById(id);

        if (staff.isPresent()) {
            return new ResponseEntity<>(staff.get(), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Staff>> findAllStaff() {
        if (staffService.findAll().isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(staffService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findByFirstName/{firstname}")
    public ResponseEntity<List<Staff>> findByFirstName(
            @PathVariable("firstname") String firstname
    ) {
        if (staffService.findByFirstName(firstname).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(staffService.findByFirstName(firstname), HttpStatus.OK);
    }

    @GetMapping("/findByActive")
    public ResponseEntity<List<Staff>> findByActiveStaff() {
        if (staffService.findByActiveStaff().isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(staffService.findByActiveStaff(), HttpStatus.OK);
    }

    @GetMapping("/findByInactive")
    public ResponseEntity<List<Staff>> findByInctiveStaff() {
        if (staffService.findByInactiveStaff().isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(staffService.findByInactiveStaff(), HttpStatus.OK);
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<Staff> findByEmail(
            @PathVariable("email") String email
    ) {
        if (staffService.findByEmail(email) == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(staffService.findByEmail(email), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Staff> save(
            @RequestBody Staff staff
    ) {
        //ensure that the email entered is unique
        if (staffService.findByEmail(staff.getEmail()) != null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        if (staff.getEmail() == null || staff.getFirstname() == null || staff.getLastname() == null || staff.getAddress() == null || staff.getPhone() == null || staff.getPicture() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (staff.getEmail().isEmpty() || staff.getFirstname().isEmpty() || staff.getLastname().isEmpty() || staff.getAddress().isEmpty() || staff.getPhone().isEmpty() || staff.getPicture().isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(staffService.save(staff), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Staff> updateStaff(
            @PathVariable("id") Integer id,
            @RequestBody Staff staff
    ) {
        Optional<Staff> staff1 = staffService.findById(id);

        if (staff1.isPresent()) {
            if (staff.getEmail() == null || staff.getFirstname() == null || staff.getLastname() == null || staff.getAddress() == null || staff.getPhone() == null || staff.getPicture() == null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            if (staff.getEmail().isEmpty() || staff.getFirstname().isEmpty() || staff.getLastname().isEmpty() || staff.getAddress().isEmpty() || staff.getPhone().isEmpty() || staff.getPicture().isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            Staff updatedStaff = staffService.updateStaff(id, staff);
            return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<Staff> activateStaff(
            @PathVariable("id") Integer id
    ) {
        Optional<Staff> staff = staffService.findById(id);

        if (staff.isPresent()) {
            Staff activatedStaff = staffService.activateStaff(id);
            return new ResponseEntity<>(activatedStaff, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<Staff> deactivateStaff(
            @PathVariable("id") Integer id
    ) {
        Optional<Staff> staff = staffService.findById(id);

        if (staff.isPresent()) {
            Staff deactivatedStaff = staffService.deactivateStaff(id);
            return new ResponseEntity<>(deactivatedStaff, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Integer id
    ) {
        Optional<Staff> staff = staffService.findById(id);

        if (staff.isPresent()) {
            staffService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
