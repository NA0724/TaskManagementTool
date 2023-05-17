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

    public User createUser(User user) {
        return userRepository.insert(user);
    }

    public User updateUser(String username, User user) {
        Optional<User> optUser = userRepository.getUserByUsername(username);
        User u = optUser.get(); 
        if(u.getFirstname()!= null) {
            u.setFirstname(user.getFirstname());
        }
        if(u.getLastname()!= null) {
            u.setLastname(user.getLastname());
        }
        if(u.getEmail()!= null) {
            u.setEmail(user.getEmail());
        }
        if(u.getUsername()!= null) {
            u.setUsername(user.getUsername());
        }
        if(u.getPassword()!= null) {
            u.setPassword(user.getPassword());
        }
        userRepository.save(u);
        return u;
    }

    public void deleteUser(String username) {
        Optional<User> user = userRepository.getUserByUsername(username);
        ObjectId id = user.get().getId();
        userRepository.deleteById(id);
    }
    
}
