package com.ounitech.wemove.user;


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
    public User save(
            @RequestBody User user
    ) {
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public Optional<User> findById(
            @PathVariable("id") Integer id
    ) {
        return userService.findById(id);
    }

    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAll();
    }

    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable("id") Integer id,
            @RequestBody User user
    ) {
        Optional<User> userById = userService.findById(id);
        System.out.println(userById);

        if (userById.isPresent()) {
            User _user = userById.get();
            _user.setFirstname(user.getFirstname());
            _user.setLastname(user.getLastname());
            _user.setEmail(user.getEmail());
            _user.setJob(user.getJob());

            return userService.save(_user);
        } else
            return null;
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable("id") Integer id
    ) {
        userService.deleteById(id);
    }

}
