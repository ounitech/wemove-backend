package com.example.simple.spring.project.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    public User save(
            @RequestBody User user
    ) {
        return repository.save(user);
    }

    @GetMapping("/{id}")
    public Optional<User> findById(
            @PathVariable("id") Integer id
    ) {
        return repository.findById(id);
    }

    @GetMapping
    public List<User> findAllUsers() {
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable("id") Integer id,
            @RequestBody User user
    ) {
        Optional<User> userById = repository.findById(id);
        System.out.println(userById);

        if (userById.isPresent()) {
            User _user = userById.get();
            _user.setFirstname(user.getFirstname());
            _user.setLastname(user.getLastname());
            _user.setEmail(user.getEmail());
            _user.setJob(user.getJob());

            return repository.save(_user);
        } else
            return null;
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable("id") Integer id
    ) {
        repository.deleteById(id);
    }

}
