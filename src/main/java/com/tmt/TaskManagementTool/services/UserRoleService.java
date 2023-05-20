package com.tmt.TaskManagementTool.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.tmt.TaskManagementTool.models.Role;
import com.tmt.TaskManagementTool.models.User;
import com.tmt.TaskManagementTool.repositories.RoleRepository;
import com.tmt.TaskManagementTool.repositories.UserRepository;

public class UserRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;
    
    public Role getRoleByUsername(String username){
        Optional<User> user = userRepository.getUserByUsername(username);
        return null;
        
    }
}
