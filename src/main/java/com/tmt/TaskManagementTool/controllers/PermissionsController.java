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
import com.tmt.TaskManagementTool.services.PermissionService;

@RestController
@RequestMapping("/api/v1/permissions")
public class PermissionsController {

    @Autowired
    private PermissionService permissionService;

     /*
     * showAllUsers(): show all users in the database
     */
    @GetMapping
    public ResponseEntity<List<Permission>> showAllPermissions(){
        return new ResponseEntity<List<Permission>>(permissionService.getAllPermission(), HttpStatus.OK);
    }

    /*
     * create a new user in the database
     */
    @GetMapping("/create-Permission/{rid}")
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission, String rid){
        return new ResponseEntity<Permission>(permissionService.createPermissionForRole(permission, rid), HttpStatus.CREATED);
    }

    /*
     * search and update a user in the database by id
     */
    @PutMapping("/edit-Permission/{pid}")
    public ResponseEntity<Permission> updatePermission(@PathVariable String pid, @RequestBody Permission permission){
        return new ResponseEntity<Permission>(permissionService.updatePermission(pid, permission), HttpStatus.OK);
    }

    /*
     * delete a user in the database by username
     */
    @GetMapping("/delete-Permission/{pid}")
    public void deletePermission(@PathVariable String pid){
        permissionService.deletePermission(pid);
    }

      /*
     * search and get a user in the database by id
     */
    @GetMapping("/search-id/{id}")
    public ResponseEntity<Optional<Permission>> getPermissionById(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<Permission>>(permissionService.getPermissionById(id),HttpStatus.OK) ;
    }
    
    @GetMapping("/search-Permission/{rid}")
    public ResponseEntity<Optional<Permission>> getPermissionByPid(@PathVariable String pid){
        return new ResponseEntity<Optional<Permission>>(permissionService.getPermissionByPid(pid),HttpStatus.OK) ;
    }
    
}
