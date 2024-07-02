package com.ounitech.wemove.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public User updateUser(Integer id, User user) {
        Optional<User> userById = userRepository.findById(id);

        User _user = userById.get();
        _user.setFirstname(user.getFirstname());
        _user.setLastname(user.getLastname());
        _user.setEmail(user.getEmail());
        _user.setJob(user.getJob());

        return userRepository.save(_user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
