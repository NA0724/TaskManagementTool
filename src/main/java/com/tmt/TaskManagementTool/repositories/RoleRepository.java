package com.tmt.TaskManagementTool.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.tmt.TaskManagementTool.models.Role;

public interface RoleRepository extends MongoRepository<Role, ObjectId>{
    Optional<Role> getRoleByName(String name);
    Optional<Role> getRoleById(String id); 
}
