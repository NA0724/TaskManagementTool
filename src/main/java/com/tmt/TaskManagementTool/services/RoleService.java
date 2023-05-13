package com.tmt.TaskManagementTool.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmt.TaskManagementTool.models.Role;
import com.tmt.TaskManagementTool.repositories.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(ObjectId id) {
        return roleRepository.findById(id);
    }

    public Optional<Role> getRoleByRid(String rid) {
        return roleRepository.getRoleByRid(rid);
    }

    public Optional<Role> getRoleByName(String name) {
        return roleRepository.getRoleByName(name);
    }

    public Role createRole(Role role) {
        Role newRole = new Role();
        newRole.setName(role.getName());
        newRole.setRid(role.getRid());
        roleRepository.save(newRole);
        return roleRepository.insert(role);
    }

    public Role updateRole(String name, Role role) {
        Optional<Role> optRole = roleRepository.getRoleByName(name);
        Role r = optRole.get(); 
        if (r.getRid() != null && r.getName() != null) {
            r.setRid(role.getRid());
            r.setName(role.getName());
        }
        return r;
    }

    public void deleteRole(String name) {
        Optional<Role> role = roleRepository.getRoleByName(name);
        ObjectId id = role.get().getId();
        roleRepository.deleteById(id);
    }

    
}
