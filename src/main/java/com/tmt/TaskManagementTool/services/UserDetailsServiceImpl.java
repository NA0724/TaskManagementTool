package com.tmt.TaskManagementTool.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tmt.TaskManagementTool.models.User;
import com.tmt.TaskManagementTool.repositories.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userOptional = userRepository.getUserByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("No user Found with username : " + username));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, getAuthorities("USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    UserDetails loadUserByName(String username) throws IllegalAccessException {
        User user = userRepository.getUserByUsername(username).orElseThrow(IllegalAccessException::new);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),true, true, true, true, getAuthorities("USER"));
    }

    
}
