package com.zfl.demo.domain.user;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return Lists.newArrayList(userRepository.findAll());
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    public User createUser(User user) {
        if (userRepository.existsByName(user.getName())) {
            throw new EntityExistsException("User with name " + user.getName() + " already exists");
        } else {
            return userRepository.save(user);
        }
    }


    public User updateUser(Long id, User user) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User with id " + id + " not found");
        } else {
            return userRepository.save(user);
        }
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User with id " + id + " not found");
        } else {
            userRepository.deleteById(id);
        }
    }
}
