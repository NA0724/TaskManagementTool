package com.tmt.TaskManagementTool.controllers;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmt.TaskManagementTool.models.User;
import com.tmt.TaskManagementTool.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    /*
     * showAllUsers(): show all users in the database
     */
    @GetMapping
    public ResponseEntity<List<User>> showAllUsers(){
        return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
    }

    /*
     * create a new user in the database
     */
    @GetMapping("/createUser")
    public ResponseEntity<List<User>> createUser(){
        return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
    }

    /*
     * search and get a user in the database by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<User>>(userService.getUserById(id), HttpStatus.OK);
    }

    /*
     * search and get a user in the database by email
     */
    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<Optional<User>> getUserByEmail(@PathVariable String email){
        return new ResponseEntity<Optional<User>>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    /*
     * search and get a user in the database by username
     */
    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<Optional<User>> getUserByUsername(@PathVariable String username){
        return new ResponseEntity<Optional<User>>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    /*
     * search and update a user in the database by id
     */
    @GetMapping("/updateUser/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username){
        return new ResponseEntity<User>(userService.updateUser(username), HttpStatus.OK);
    }

    /*
     * delete a user in the database by username
     */
    @GetMapping("/deleteUser/{username}")
    public void deleteUser(@PathVariable String username){
        userService.deleteUser(username);
    }
    
}
