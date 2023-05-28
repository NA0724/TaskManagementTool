package com.tmt.TaskManagementTool.controllers;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmt.TaskManagementTool.models.Permission;
import com.tmt.TaskManagementTool.models.Role;
import com.tmt.TaskManagementTool.services.PermissionService;
import com.tmt.TaskManagementTool.services.RoleService;



@RestController
@RequestMapping("/api/v1/roles")
public class RolesController {
    
    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /*
     * showAllUsers(): show all roles in the database
     */
    @GetMapping
    public ResponseEntity<List<Role>> showAllRoles(){
        return new ResponseEntity<List<Role>>(roleService.getAllRole(), HttpStatus.OK);
    }

    /*
     * search and update a role in the database 
     */
    @PutMapping("/edit-role/{name}")
    public ResponseEntity<Role> updateRole(@PathVariable String name, @RequestBody Role role){
        return new ResponseEntity<Role>(roleService.updateRole(name, role), HttpStatus.OK);
    }

    /*
     * delete a role in the database by username
     */
    @GetMapping("/delete-role/{name}")
    public void deleteRole(@PathVariable String name){
        roleService.deleteRole(name);
    }

    /*
     * create a new permission for in the database
     */
    @GetMapping("/create-Permission/{rid}")
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission, String rid){
        return new ResponseEntity<Permission>(permissionService.createPermissionForRole(permission, rid), HttpStatus.CREATED);
    }

    /*
     * delete a user in the database by username
     */
    @GetMapping("/delete-Permission/{pid}")
    public void deletePermission(@PathVariable String pid){
        permissionService.deletePermission(pid);
    }
    
}
