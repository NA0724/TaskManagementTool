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

import com.tmt.TaskManagementTool.models.Role;
import com.tmt.TaskManagementTool.services.RoleService;



@RestController
@RequestMapping("/api/v1/roles")
public class RolesController {
    @Autowired
    private RoleService roleService;

    /*
     * showAllUsers(): show all users in the database
     */
    @GetMapping
    public ResponseEntity<List<Role>> showAllRoles(){
        return new ResponseEntity<List<Role>>(roleService.getAllRole(), HttpStatus.OK);
    }

    /*
     * create a new user in the database
     */
    @GetMapping("/create-role")
    public ResponseEntity<Role> createRole(@RequestBody Role role){
        return new ResponseEntity<Role>(roleService.createRole(role), HttpStatus.CREATED);
    }

    /*
     * search and update a user in the database by id
     */
    @PutMapping("/edit-role/{name}")
    public ResponseEntity<Role> updateRole(@PathVariable String name, @RequestBody Role role){
        return new ResponseEntity<Role>(roleService.updateRole(name, role), HttpStatus.OK);
    }

    /*
     * delete a user in the database by username
     */
    @GetMapping("/delete-role/{name}")
    public void deleteRole(@PathVariable String name){
        roleService.deleteRole(name);
    }

      /*
     * search and get a user in the database by id
     */
    @GetMapping("/search-id/{id}")
    public ResponseEntity<Optional<Role>> getRoleById(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<Role>>(roleService.getRoleById(id),HttpStatus.OK) ;
    }
    
    @GetMapping("/search-role/{rid}")
    public ResponseEntity<Optional<Role>> getRoleByRid(@PathVariable String rid){
        return new ResponseEntity<Optional<Role>>(roleService.getRoleByRid(rid),HttpStatus.OK) ;
    }


    
}
