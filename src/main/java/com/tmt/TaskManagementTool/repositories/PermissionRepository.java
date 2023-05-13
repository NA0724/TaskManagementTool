package com.tmt.TaskManagementTool.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.tmt.TaskManagementTool.models.Permission;

public interface PermissionRepository extends MongoRepository<Permission, ObjectId>{
    
    
}
