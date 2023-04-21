package com.tmt.TaskManagementTool.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmt.TaskManagementTool.models.User;
import com.tmt.TaskManagementTool.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(ObjectId id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    public User createUser() {
        User user = new User();
        userRepository.save(user);
        return userRepository.insert(user);
    }

    public User updateUser(String username) {
        return userRepository.updateUser(username);
    }

    public void deleteUser(String username) {
        Optional<User> user = userRepository.getUserByUsername(username);
        ObjectId id = user.get().getId();
        userRepository.deleteById(id);
    }
    
}
