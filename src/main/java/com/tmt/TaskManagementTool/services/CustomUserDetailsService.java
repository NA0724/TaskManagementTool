package com.tmt.TaskManagementTool.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tmt.TaskManagementTool.models.User;
import com.tmt.TaskManagementTool.repositories.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.getUserByUsername(username);
        return new org.springframework.security.core.userdetails.User(
            user.get().getUsername(),
            user.get().getPassword(), 
            null);

    }
    
}
