package com.ounitech.wemove.user;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> save(
            @RequestBody User user
    ) {
        if (user.getEmail() == null || user.getJob() == null || user.getFirstname() == null || user.getLastname() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (user.getEmail().isEmpty() || user.getJob().isEmpty() || user.getFirstname().isEmpty() || user.getLastname().isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(
            @PathVariable("id") Integer id
    ) {
        Optional<User> user = userService.findById(id);

        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        if (userService.findAll().isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable("id") Integer id,
            @RequestBody User user
    ) {
        Optional<User> userById = userService.findById(id);

        if (userById.isPresent()) {
            if (user.getEmail() == null || user.getJob() == null || user.getFirstname() == null || user.getLastname() == null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            if (user.getEmail().isEmpty() || user.getJob().isEmpty() || user.getFirstname().isEmpty() || user.getLastname().isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            User _user = userById.get();
            _user.setFirstname(user.getFirstname());
            _user.setLastname(user.getLastname());
            _user.setEmail(user.getEmail());
            _user.setJob(user.getJob());

            return new ResponseEntity<>(userService.save(_user), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Integer id
    ) {
        Optional<User> user = userService.findById(id);

        if (user.isPresent()) {
            userService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
