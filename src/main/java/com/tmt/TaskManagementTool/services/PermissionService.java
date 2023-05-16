package com.tmt.TaskManagementTool.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmt.TaskManagementTool.models.Permission;
import com.tmt.TaskManagementTool.repositories.PermissionRepository;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public List<Permission> getAllPermission() {
        return permissionRepository.findAll();
    }

    public Optional<Permission> getPermissionById(ObjectId id) {
        return permissionRepository.findById(id);
    }

    public Optional<Permission> getPermissionByPid(String pid) {
        return permissionRepository.getPermissionByPid(pid);
    }

    public Optional<Permission> getPermissionByName(String name) {
        return permissionRepository.getPermissionByName(name);
    }

    public Permission createPermission(Permission Permission) {
        Permission newPermission = new Permission();
        newPermission.setName(Permission.getName());
        newPermission.setPid(Permission.getPid());
        permissionRepository.save(newPermission);
        return permissionRepository.insert(Permission);
    }

    public Permission updatePermission(String pid, Permission Permission) {
        Optional<Permission> optPermission = permissionRepository.getPermissionByPid(pid);
        Permission r = optPermission.get(); 
        if (r.getPid() != null && r.getName() != null) {
            r.setPid(Permission.getPid());
            r.setName(Permission.getName());
        }
        return r;
    }

    public void deletePermission(String pid) {
        Optional<Permission> Permission = permissionRepository.getPermissionByPid(pid);
        ObjectId id = Permission.get().getId();
        permissionRepository.deleteById(id);
    }

    
}
