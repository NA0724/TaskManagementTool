package com.tmt.TaskManagementTool.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.tmt.TaskManagementTool.models.Permission;

public interface PermissionRepository extends MongoRepository<Permission, ObjectId>{

    Optional<Permission> getPermissionById(String id);
    Optional<Permission> getPermissionByPid(String pid);
    Optional<Permission> getPermissionByName(String name); 
}
